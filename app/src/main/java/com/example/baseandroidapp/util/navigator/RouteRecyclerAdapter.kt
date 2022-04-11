package com.example.baseandroidapp.util.navigator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidapp.R

class RouteRecyclerAdapter() : RecyclerView.Adapter<RouteRecyclerAdapter.ViewHolder>() {

    var itemList = mutableListOf(Navigator.Destination.Navigation, Navigator.Destination.MotionLayout)

    var itemClickListener : ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_route, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btnMenu.setOnClickListener {
            itemClickListener?.invoke(itemList[position].title)
        }

        holder.btnMenu.text = itemList[position].title

    }

    override fun getItemCount(): Int = itemList.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnMenu: Button = itemView.findViewById(R.id.btn_route)
    }



}