package com.devhoony.baseandroidapp.vod

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.devhoony.baseandroidapp.databinding.ActivityVodDetailBinding
import com.devhoony.baseandroidapp.util.DLog
import com.devhoony.baseandroidapp.util.extension.loadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VodDetailActivity : AppCompatActivity() {

    private val vodViewModel: VodDetailViewModel by viewModels()

    private lateinit var binding :ActivityVodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // view binding
        binding = ActivityVodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // data binding
//        binding = DataBindingUtil.setContentView(this@VodDetailActivity, R.layout.activity_vod_detail)


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