package com.example.apiintegration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiintegration.R
import com.example.apiintegration.model.Post

class PostsDataAdapter(


): RecyclerView.Adapter<PostsDataAdapter.ViewHolder>() {

    private val items = mutableListOf<Post>()

    fun submitList( list: List<Post> ){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostsDataAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_data_items, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsDataAdapter.ViewHolder, position: Int) {
        val item = items[position]

        holder.title.text = item.title
        holder.views.text =" ${item.views?.toString()} views"?: "0"

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val views = itemView.findViewById<TextView>(R.id.tvViews)

    }
}