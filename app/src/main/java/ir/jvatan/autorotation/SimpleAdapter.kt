package ir.jvatan.autorotation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tomoima.infiniterotation.R
import kotlinx.android.synthetic.main.item_simple.view.*


class SimpleAdapter(itemList: List<ItemInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: List<ItemInfo> = listOf(itemList.last()) + itemList + listOf(itemList.first())

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? ItemViewHolder)?.itemView?.let {
            it.pageName.text = list[position % list.size].page
            it.vFirst.setBackgroundColor(list[position % list.size].colorInt)
            it.vSecond.setBackgroundColor(list[(position + 1) % list.size].colorInt)

            it.pagePosition.text =
                    it.resources.getString(R.string.actual_position, position.toString())

            if (position % 2 == 0)
                it.setPadding(0, -100, 0, 0)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_simple, parent, false))


    override fun getItemCount() = Integer.MAX_VALUE

    internal class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}