package win.tommy.mykotlin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_kotlin.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import win.tommy.mykotlin.R
import win.tommy.mykotlin.bean.Comic
import win.tommy.mykotlin.domain.net.ComicSource
import win.tommy.mykotlin.ui.activity.fragment.ComicFragment

class ComicActivity : AppCompatActivity() {
    companion object {
        val INTENT_COMIC_URL = "url"
    }

    lateinit var url: String
    var mData = ArrayList<Comic>()
    lateinit var adapter: ComicPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        url = intent.getStringExtra(INTENT_COMIC_URL)
        adapter = ComicPagerAdapter(mData, supportFragmentManager)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 2
    }

    //创建一个伴生对象
//    companion object {
//        val USER = "USER"
//        fun startActivity(context: Context,user: UserBean){
//            var intent = Intent(context, ComicActivity::class.java)
//            intent.putExtra(USER,user)
//            context.startActivity(intent)
//            Log.e("twb",user.name+user.id)
//        }
//    }

    override fun onResume() {
        super.onResume()
        doAsync {
            val data = ComicSource().obtain(url)
            Log.e("twb", "==" + data)
            mData = data

            uiThread {
                adapter.refreshData(data)
            }
        }
    }

    inner class ComicPagerAdapter(var data: java.util.ArrayList<Comic> = java.util.ArrayList<Comic>(), fragmentManager: FragmentManager) :
            FragmentPagerAdapter(fragmentManager) {
        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Fragment? = newInstance(data[position].comicUrl)

        fun refreshData(newData: java.util.ArrayList<Comic>) {
            data = newData
            notifyDataSetChanged()
        }

        fun newInstance(url: String) = ComicFragment.instance(url)
    }
}
