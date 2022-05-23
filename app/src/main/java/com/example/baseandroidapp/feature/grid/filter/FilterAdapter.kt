package com.example.baseandroidapp.feature.grid.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidapp.R
import com.example.baseandroidapp.databinding.ItemFilterCheckboxBinding
import com.example.baseandroidapp.util.DLog

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    val filterViewList: MutableList<FilterView> = arrayListOf()
    var itemClickListener: ((FilterView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_filter_checkbox,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filterViewList[position], position)
    }

    override fun getItemCount(): Int = filterViewList.size

    fun addList(list: List<FilterView>) {
        if (filterViewList.isNotEmpty()) filterViewList.clear()
        list.forEach {
            filterViewList.add(it.copy())
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemFilterCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FilterView, position: Int) {
            binding.data = data
//            binding.cbContent.text = data.name
            binding.cbContent.isChecked = data.isChecked
            binding.cbContent.setOnClickListener {
                val b = binding.cbContent.isChecked
                filterViewList[position] = filterViewList[position].copy(isChecked = b)
                itemClickListener?.invoke(data)
                DLog.e("list : ${filterViewList.toString()}")
                notifyItemChanged(position)
            }
        }
    }
}