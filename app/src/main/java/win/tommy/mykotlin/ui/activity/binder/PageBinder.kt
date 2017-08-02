package win.tommy.mykotlin.ui.activity.binder


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_page.view.*
import win.tommy.mykotlin.R
import win.tommy.mykotlin.bean.Page
import win.tommy.mykotlin.ui.activity.adapter.AnotherBinder
import win.tommy.mykotlin.ui.activity.adapter.AnotherViewHolder

/**
 * @author wupanjie
 */
class PageBinder : AnotherBinder<Page>() {
  override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): AnotherViewHolder {
    val itemView = inflater.inflate(R.layout.item_page, parent, false)
    return AnotherViewHolder(itemView)
  }

  override fun renderView(holder: AnotherViewHolder, itemView: View, item: Page) {
    itemView.tvPage.text = item.title
  }
}