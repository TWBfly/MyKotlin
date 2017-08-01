package win.tommy.mykotlin.ui.activity.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_cover.view.*
import win.tommy.mykotlin.R
import win.tommy.mykotlin.bean.Cover
import win.tommy.mykotlin.ui.activity.adapter.AnotherBinder
import win.tommy.mykotlin.ui.activity.adapter.AnotherViewHolder
import win.tommy.mykotlin.util.loadUrl


class CoverBinder: AnotherBinder<Cover>() {
    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): AnotherViewHolder {
        val view = inflater.inflate(R.layout.item_cover, parent, false)
        return AnotherViewHolder(view)
    }

    override fun renderView(holder: AnotherViewHolder, itemView: View, item: Cover) {
        itemView.tvCover.text = item.title
        itemView.ivCover.loadUrl(item.coverUrl!!)
    }
}