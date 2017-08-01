package win.tommy.mykotlin.ui.activity.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup


class ContentPagerAdapter(val fragments: ArrayList<Fragment>, val nameList:List<String>, fm:FragmentManager) : FragmentPagerAdapter(fm)  {
    override fun getItem(position: Int): Fragment? = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence = nameList[position]

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
//        container.removeView((View)object)

    }
}