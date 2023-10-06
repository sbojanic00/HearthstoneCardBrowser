package hr.algebra.hearthstonecardbrowser.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import hr.algebra.hearthstonecardbrowser.R
import hr.algebra.hearthstonecardbrowser.dao.entities.CardEntity


class ItemPagerAdapter(private val items: MutableList<CardEntity>)
    : RecyclerView.Adapter<ItemPagerAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)

        private val tvCardBackName = itemView.findViewById<TextView>(R.id.tvCardbackName)
        private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)


        fun bind(item: CardEntity) {
            ivItem.load(item.imgPath) {
                placeholder(R.drawable.hearthstone_logo_text)
                error(R.drawable.hearthstone_logo_text)
            }

            tvCardBackName.text = item.name
            tvDescription.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pager, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }


    override fun getItemCount() = items.size
}