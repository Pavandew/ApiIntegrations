package com.example.apiintegration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiintegration.R
import com.example.apiintegration.model.PostData

class PostAdapter(
    var itemList: List<PostData>
): RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostAdapter.ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.post_items, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val items = itemList[position]

        holder.userId.text = items.userId.toString()
        holder.id.text = items.id.toString()
        holder.title.text = items.title.toString()
        holder.body.text = items.body.toString()

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var userId = itemView.findViewById<TextView>(R.id.tvUserId)!!
        val id = itemView.findViewById<TextView>(R.id.tvId)!!
        val title = itemView.findViewById<TextView>(R.id.tvTitle)!!
        val body = itemView.findViewById<TextView>(R.id.tvBody)!!
    }

}