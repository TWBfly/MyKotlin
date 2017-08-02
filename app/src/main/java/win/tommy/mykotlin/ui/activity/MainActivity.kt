package win.tommy.mykotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import win.tommy.mykotlin.R
import win.tommy.mykotlin.ui.activity.adapter.ContentPagerAdapter
import win.tommy.mykotlin.ui.activity.fragment.BookFragment
import win.tommy.mykotlin.ui.activity.fragment.HomeFragment
import win.tommy.mykotlin.ui.activity.fragment.NewsFragment

class MainActivity : AppCompatActivity() {

    companion object {
        val url="https://github.com/TWBfly/MyKotlin"
    }
    val nameResList : ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { jump2MyGithub() }
        var fragments = ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(BookFragment())
        fragments.add(NewsFragment())

        val nameList = nameResList.map (this::getString)//将Int类型集合遍历成String类型
        val contentPagerAdapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)
        viewPager.adapter = contentPagerAdapter
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun jump2MyGithub() {
        val intent = Intent(this,GitHubActivity().javaClass)
        intent.putExtra("GITURL", url)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.action_about ->Log.e("twb","被点击了...")
        }

        return true
    }
}
