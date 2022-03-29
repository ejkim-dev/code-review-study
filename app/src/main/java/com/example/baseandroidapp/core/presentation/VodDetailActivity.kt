package com.example.baseandroidapp.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidapp.R
import com.example.baseandroidapp.databinding.ActivityVodDetailBinding
import com.example.baseandroidapp.databinding.ItemVodBinding
import com.example.baseandroidapp.util.extension.loadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VodDetailActivity: AppCompatActivity() {

    private val vodViewModel: VodDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_vod_detail)

        val binding = ActivityVodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vodViewModel.vodData.observe(this){
            binding.tvVodTitle.text = it.title
            binding.ivVod.loadFromUrl(it.imageUrl)
            binding.tvVodDescription.text = it.description
        }



        vodViewModel.loadVodData(intent.getIntExtra("vod", 0))

    }
}