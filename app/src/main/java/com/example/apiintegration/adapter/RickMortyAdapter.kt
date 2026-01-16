package com.example.apiintegration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apiintegration.R
import com.example.apiintegration.model.Result
import com.example.apiintegration.model.RickMortyData

class RickMortyAdapter: RecyclerView.Adapter<RickMortyAdapter.ViewHolder>() {

    private val items = mutableListOf<Result>()

    fun submitList(newItems: List<Result>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickMortyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rick_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RickMortyAdapter.ViewHolder, position: Int) {

        val item = items[position]

        holder.name.text = item.name
        holder.gender.text = item.gender
        holder.image.load(item.image)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.ivImage)
        val name = itemView.findViewById<TextView>(R.id.tvName)
        val gender = itemView.findViewById<TextView>(R.id.tvGender)

    }

}