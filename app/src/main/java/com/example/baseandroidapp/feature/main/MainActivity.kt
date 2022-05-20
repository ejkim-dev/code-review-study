package com.example.baseandroidapp.feature.main

import android.view.LayoutInflater
import com.example.baseandroidapp.databinding.ActivityMainBinding
import com.example.baseandroidapp.util.base.ViewBindingActivity

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    override fun inflateLayout(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)


}