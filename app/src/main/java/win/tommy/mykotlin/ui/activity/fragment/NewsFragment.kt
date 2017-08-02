package win.tommy.mykotlin.ui.activity.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import win.tommy.mykotlin.R
import win.tommy.mykotlin.bean.NewsContainer
import win.tommy.mykotlin.domain.net.NewsSource
import win.tommy.mykotlin.ui.activity.adapter.AnotherAdapter
import win.tommy.mykotlin.ui.activity.binder.NewsContainerBinder
import java.util.*


class NewsFragment: Fragment() {
    companion object {
        val AIM_URL = "http://ishuhui.net/CMS/"
    }

    var mData = ArrayList<NewsContainer>()

    lateinit var newsList: RecyclerView

    lateinit var newsRefresh: SwipeRefreshLayout

    lateinit var adapter: AnotherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsRefresh = view.homeRefresh
        newsList = view.homeList

        val layoutManager = LinearLayoutManager(context)
//    layoutManager.initialPrefetchItemCount = 2
        layoutManager.setInitialPrefetchItemCount(2)
        newsList.layoutManager = layoutManager
        adapter = AnotherAdapter().with(NewsContainer::class.java, NewsContainerBinder())
        newsList.adapter = adapter

        newsRefresh.setOnRefreshListener {
            load()
        }
        newsRefresh.post { newsRefresh.isRefreshing = true }
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {

            load()
        }

    }

    private fun load() {
        doAsync {
            val data = NewsSource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.update(data)
                newsRefresh.isRefreshing = false
            }
        }
    }

}