package win.tommy.mykotlin.ui.activity.binder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_news.view.*
import win.tommy.mykotlin.R
import win.tommy.mykotlin.bean.News
import win.tommy.mykotlin.ui.activity.adapter.AnotherBinder
import win.tommy.mykotlin.ui.activity.adapter.AnotherViewHolder

/**
 * @author wupanjie
 */
class NewsBinder : AnotherBinder<News>() {
  override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): AnotherViewHolder {
    val view = inflater.inflate(R.layout.item_news, parent, false)
    return AnotherViewHolder(view)
  }

  override fun renderView(holder: AnotherViewHolder, itemView: View, item: News) {
    itemView.tvTitle.text = item.title
    itemView.tvTime.text = item.createdTime
    if (holder.adapterPosition % 2 == 0) {
      itemView.container.setBackgroundResource(R.color.alpha_grey)
    }else{
      itemView.container.setBackgroundResource(R.color.material_white)
    }

    itemView.container.setOnClickListener {
//      WebDetailDialog(itemView.context, item, NewsDetailSource())
    }
  }

}