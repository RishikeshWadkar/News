package com.rishikeshwadkar.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*

class NewsAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val item: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent,false)
        val holder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(item[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = item[position]
        holder.title.text = currentItem.title
        holder.authorTV.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun updateNews(updatedNews: ArrayList<News>){
        item.clear()
        item.addAll(updatedNews)

        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title: TextView = itemView.findViewById(R.id.itemTitle)
    val authorTV: TextView = itemView.findViewById(R.id.itemAuthorTV)
    val itemImage: ImageView = itemView.findViewById(R.id.itemImageView)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}