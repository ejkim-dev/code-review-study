package com.example.baseandroidapp.vod

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidapp.databinding.ActivityVodDetailBinding
import com.example.baseandroidapp.util.DLog
import com.example.baseandroidapp.util.extension.loadFromUrl
import com.example.presentation.view.VodView
import com.example.presentation.viewmodel.VodDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VodDetailActivity : AppCompatActivity() {

    private val vodViewModel: VodDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vodViewModel.vodData.observe(this) {
            binding.tvVodTitle.text = it.title
            binding.ivVod.loadFromUrl(it.imageUrl)
            binding.tvVodDescription.text = it.description
        }

        vodViewModel.failure.observe(this) {
            DLog.e("fail $it")
            Toast.makeText(this@VodDetailActivity, "fail : $it", Toast.LENGTH_SHORT).show()
        }



        intent.getParcelableExtra<VodView>("vod")?.let {
            vodViewModel.loadVodData(it.id)
        }

    }
}