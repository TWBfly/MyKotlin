package win.tommy.mykotlin.ui.activity.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_book.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import win.tommy.mykotlin.R
import win.tommy.mykotlin.bean.Cover
import win.tommy.mykotlin.domain.net.BookSource
import win.tommy.mykotlin.ui.activity.BookDetailActivity
import win.tommy.mykotlin.ui.activity.adapter.AnotherAdapter
import win.tommy.mykotlin.ui.activity.binder.CoverBinder


class BookFragment : Fragment() {

    companion object {
        val AIM_URL = "http://ishuhui.net/ComicBookList/"
    }

    lateinit var bookRefresh: SwipeRefreshLayout
    lateinit var bookList: RecyclerView
    lateinit var adapter: AnotherAdapter
    var mData= ArrayList<Cover>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater?.inflate(R.layout.fragment_book, container, false)
        return inflate
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    private fun initView(view: View?) {
        bookRefresh = view?.find(R.id.book_refresh)!!
        bookList = view.book_list

        bookList.layoutManager = GridLayoutManager(context, 3)
        adapter = AnotherAdapter().with(Cover::class.java, CoverBinder().clickWith { item, _ -> jump2Detail(item) })
        bookList.adapter = adapter
        bookRefresh.setOnRefreshListener {
            load()
        }
        bookRefresh.post { bookRefresh.isRefreshing = true }


    }

    private fun load() {
        doAsync {
            val data = BookSource().obtain(AIM_URL)

            uiThread {
                mData = data
                adapter.update(data)
                bookRefresh.isRefreshing = false
            }
        }

    }

    private fun jump2Detail(cover: Cover) {
        val intent = Intent(context, BookDetailActivity().javaClass)

        intent.putExtra(BookDetailActivity.INTENT_COVER_URL, cover.coverUrl)
        intent.putExtra(BookDetailActivity.INTENT_URL, cover.link)
        intent.putExtra(BookDetailActivity.INTENT_TITLE, cover.title)
        startActivity(intent)

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }

    }
}


