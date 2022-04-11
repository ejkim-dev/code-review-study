package com.example.baseandroidapp.sample.navigation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseandroidapp.R

class NavigationActivity : AppCompatActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, NavigationActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
    }
}