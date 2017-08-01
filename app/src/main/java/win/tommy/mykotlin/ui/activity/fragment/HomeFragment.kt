package win.tommy.mykotlin.ui.activity.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import win.tommy.mykotlin.R
import win.tommy.mykotlin.bean.Cover
import win.tommy.mykotlin.domain.net.CoverSource
import win.tommy.mykotlin.ui.activity.adapter.AnotherAdapter
import win.tommy.mykotlin.ui.activity.binder.CoverBinder
import java.util.*


class HomeFragment: Fragment() {
    companion object {
        val AIM_URL = "http://ishuhui.net/?PageIndex=1"
    }
    //lateinit 非空类型延迟加载
    lateinit var homeRefresh: SwipeRefreshLayout
    lateinit var coverList: RecyclerView
    lateinit var adapter: AnotherAdapter
    var mData = ArrayList<Cover>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //非中断保存
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_home,container,false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View?) {
        //?是可以为空 !!是绝对不为空
        homeRefresh = view?.homeRefresh!!
        coverList = view?.homeList

        coverList.layoutManager = GridLayoutManager(context, 3)//设置recyclerview的 layoutManager
        adapter = AnotherAdapter()
                .with(Cover::class.java, CoverBinder().clickWith {
                    item, _ ->
                    jump2Comic(item)
                })
        coverList.adapter = adapter
        homeRefresh.setOnRefreshListener { load() }
        homeRefresh.post { homeRefresh.isRefreshing = true }
    }

    private fun jump2Comic(cover: Cover) {
    Log.e("twv","jump2Comic=="+cover)

    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }

    }

    private fun load() {
        doAsync{
            val data = CoverSource().obtain(AIM_URL)

            uiThread{
                mData = data
                adapter.update(data)
                homeRefresh.isRefreshing = false
            }
        }


    }



}