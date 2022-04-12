package com.example.baseandroidapp.sample.motionlayout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidapp.R
import com.example.baseandroidapp.sample.navigation.NavigationActivity

class YoutubeCloneActivity : AppCompatActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, YoutubeCloneActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_clone)
    }
}