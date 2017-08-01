package win.tommy.mykotlin.ui.activity.adapter

import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup


class AnotherAdapter : Adapter<ViewHolder>() {

    private var inflater: LayoutInflater? = null
    val items = arrayListOf<Any>()
    val types = arrayListOf<Class<*>>()
    val binders = arrayListOf<ItemBinder<*, *>>()

    fun with(clazz: Class<*>, binder: ItemBinder<*, *>): AnotherAdapter {
        if (types.contains(clazz)) {
            val index = types.indexOf(clazz)
            types.removeAt(index)
            binders.removeAt(index)
        }
        types += clazz
        binders += binder
        return this
    }
    fun update(newData: List<Any>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }
    fun insert(item: Any, position: Int) {
        if (types.contains(item.javaClass)) {
            items.add(position, item)
            notifyItemInserted(position)
        } else {
            Log.e("twb","can not find binder of this type : ${item.javaClass}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        val type = types.indexOf(item.javaClass)
        if (type == -1) {
            Log.e("twb","can not find binder of this type : ${item.javaClass}")
        }

        return type
    }
    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binder = binders[viewType]
        inflater?.let { inflater = LayoutInflater.from(parent?.context) }
        val holder = (binder as ItemBinder<Any, ViewHolder>).createViewHolder(inflater ?: LayoutInflater.from(parent?.context), parent)
        return holder

    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.let {
            val type = getItemViewType(position)
            val binder = binders[type]
            val item = items[position]

            binder.adapter = this
            (binder as ItemBinder<Any, ViewHolder>)
                    .bindViewHolder(holder, item)

            binder.itemClickListener?.let {
                holder.itemView.setOnClickListener {
                    (binder.itemClickListener as (item: Any, position: Int) -> Unit)
                            .invoke(item, holder.adapterPosition)
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getItemCount(): Int = items.size


}