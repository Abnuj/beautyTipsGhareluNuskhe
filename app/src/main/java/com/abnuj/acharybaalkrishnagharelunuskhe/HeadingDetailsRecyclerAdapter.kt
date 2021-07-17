package com.abnuj.acharybaalkrishnagharelunuskhe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HeadingDetailsRecyclerAdapter(var list: List<HeadingDetails>) : RecyclerView.Adapter<HeadingDetailsRecyclerAdapter.viewholder>() {

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews(current:HeadingDetails) {
            headingtv.setText(current.heading)
            detailstv.setText(current.details)
        }

        var headingtv = itemView.findViewById<TextView>(R.id.headingtv)
        var detailstv = itemView.findViewById<TextView>(R.id.finalDetailsTv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.heading_detail_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
val current = list[position]
        holder.bindViews(current)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}