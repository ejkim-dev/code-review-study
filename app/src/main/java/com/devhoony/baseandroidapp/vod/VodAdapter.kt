package com.devhoony.baseandroidapp.vod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devhoony.baseandroidapp.R
import com.devhoony.baseandroidapp.databinding.ItemVodBinding
import com.devhoony.baseandroidapp.util.DLog
import com.devhoony.baseandroidapp.util.extension.loadFromUrl

class VodAdapter : RecyclerView.Adapter<VodAdapter.ViewHolder>() {

    private val vodList: MutableList<VodView> = arrayListOf()
    var itemClickListener: ((VodView) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_vod,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vodList[position])
    }

    override fun getItemCount(): Int = vodList.size

    fun addList(list: List<VodView>) {
        if (vodList.isNotEmpty()) vodList.clear()
        list.forEach {
            vodList.add(it.copy())
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemVodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VodView) {
            binding.data = data
            DLog.e("thumbnail : ${data.imageUrl}")
            binding.ivVod.loadFromUrl(data.imageUrl)
            binding.root.setOnClickListener {
                itemClickListener?.invoke(data)
            }
        }
    }
}