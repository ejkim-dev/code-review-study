package com.devhoony.baseandroidapp.sample.motionlayout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devhoony.baseandroidapp.databinding.ActivityYoutubeCloneBinding

class YoutubeCloneActivity : AppCompatActivity() {

    private lateinit var binding : ActivityYoutubeCloneBinding

    companion object {
        fun callingIntent(context: Context) = Intent(context, YoutubeCloneActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityYoutubeCloneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeImageview.setOnClickListener{
            Toast.makeText(this, "close", Toast.LENGTH_SHORT).show()
            binding.mainContainer.layoutParams.height = 0
            binding.mainContainer.requestLayout()
        }

    }
}