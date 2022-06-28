package com.devhoony.baseandroidapp.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devhoony.baseandroidapp.databinding.ItemGithubRepoBinding
import com.devhoony.baseandroidapp.databinding.ItemGithubUserBinding
import com.devhoony.baseandroidapp.util.extension.loadFromUrl

class GithubAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = ArrayList<GithubInfoView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding =
                ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            UserViewHolder(binding)
        } else {
            val binding =
                ItemGithubRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            RepoViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 0) {
            dataList[position].userView?.let {
                (holder as UserViewHolder).bind(it)
            }
        } else {
            dataList[position].repoView?.let {
                (holder as RepoViewHolder).bind(it)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    override fun getItemCount() = dataList.size

    fun addList(list: List<GithubInfoView>) {
        list.map {
            dataList.add(it)
        }
        notifyDataSetChanged()
    }


    inner class RepoViewHolder(private val binding: ItemGithubRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(view: GithubRepoView) {
            binding.tvRepoName.text = view.title
            binding.tvRepoDescription.text = view.description
            binding.tvStarCount.text = view.startCount.toString()
        }
    }

    inner class UserViewHolder(private val binding: ItemGithubUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(view: GithubUserView) {
            binding.tvOwnerName.text = view.name
            binding.ivOwnerAvatar.loadFromUrl(view.profileUrl)
        }
    }


}