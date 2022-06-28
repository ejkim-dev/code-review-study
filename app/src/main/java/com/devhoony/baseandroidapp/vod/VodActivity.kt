package com.devhoony.baseandroidapp.vod

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.devhoony.baseandroidapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VodActivity : AppCompatActivity() {

    private val vodViewModel: VodViewModel by viewModels()

    private val vodAdapter = VodAdapter()

    lateinit var progressBar: ProgressBar

    companion object {
        fun callingIntent(context: Context) = Intent(context, VodActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vod)

        progressBar = findViewById(R.id.progress)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_vod)
        recyclerView.adapter = vodAdapter.apply {
            itemClickListener = {
                val intent = Intent(this@VodActivity, VodDetailActivity::class.java)
                intent.putExtra("vod", it)
                startActivity(intent)
            }
        }

        vodViewModel.loadVodList()
//        Toast.makeText(this, "using coroutine", Toast.LENGTH_SHORT).show()

        vodViewModel.vodList.observe(this) {
            renderMoviesList(it)
        }
        vodViewModel.isLoading.observe(this) {
            setProgressVisibility(it)
        }
    }


    private fun renderMoviesList(movies: List<VodView>?) {
        vodAdapter.addList(movies.orEmpty())
    }

    private fun setProgressVisibility(isVisible: Boolean) {
        progressBar.isVisible = isVisible
    }
}


