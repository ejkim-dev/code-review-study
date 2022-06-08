package com.devhoony.baseandroidapp.util.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding


abstract class ViewBindingActivity<VB : ViewBinding> : BaseActivity() {

    protected lateinit var binding : VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
    }

    abstract fun inflateLayout(layoutInflater: LayoutInflater) : VB


}