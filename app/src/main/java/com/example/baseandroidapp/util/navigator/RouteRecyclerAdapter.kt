package com.example.baseandroidapp.util.navigator

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidapp.R
import com.example.baseandroidapp.sample.navigation.NavigationActivity

class RouteRecyclerAdapter(private val mContext: Context) : RecyclerView.Adapter<RouteRecyclerAdapter.ViewHolder>() {

    var itemList = mutableListOf("navigation")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_route, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btnMenu.setOnClickListener {
            moveActivity(itemList[position], context = mContext)
        }

        holder.btnMenu.text = itemList[position]

    }

    override fun getItemCount(): Int = itemList.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnMenu: Button = itemView.findViewById(R.id.btn_route)
    }

    private fun moveActivity(value: String, context: Context) {
       val intent: Intent? = when (value) {
            "navigation" -> Intent(context, NavigationActivity::class.java)
           else-> null
        }
        intent?.let {
            context.startActivity(intent)
        }
    }

}