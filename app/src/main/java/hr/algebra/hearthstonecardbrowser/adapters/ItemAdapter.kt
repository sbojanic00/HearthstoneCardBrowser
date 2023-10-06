package hr.algebra.hearthstonecardbrowser.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import hr.algebra.hearthstonecardbrowser.ItemPagerActivity
import hr.algebra.hearthstonecardbrowser.POSITION
import hr.algebra.hearthstonecardbrowser.R
import hr.algebra.hearthstonecardbrowser.dao.entities.CardEntity
import hr.algebra.hearthstonecardbrowser.utils.framework.startActivity
import java.io.File

class ItemAdapter(private val context: Context, private val items: MutableList<CardEntity>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivCard)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        fun bind(item: CardEntity) {
            ivItem.load(item.imgPath) {
                placeholder(R.drawable.hearthstone_logo_text)
                error(R.drawable.hearthstone_logo_text)
            }
            tvTitle.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnLongClickListener {
            deleteItem(position)
            true
        }
        holder.itemView.setOnClickListener {
            context.startActivity<ItemPagerActivity>(POSITION, position)
        }

        holder.bind(item)
    }

    private fun deleteItem(position: Int) {
        val item = items[position]
//        context.contentResolver.delete(
//            ContentUris.withAppendedId(NASA_PROVIDER_CONTENT_URI, item._id!!),
//            null,
//            null
//        )
        File(item.imgPath).delete()
        items.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}