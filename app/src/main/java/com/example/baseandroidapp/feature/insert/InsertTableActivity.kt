package com.example.baseandroidapp.feature.insert

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.baseandroidapp.databinding.ActivityInsertTableBinding
import com.example.baseandroidapp.util.base.ViewBindingActivity

class InsertTableActivity : ViewBindingActivity<ActivityInsertTableBinding>() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, InsertTableActivity::class.java)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityInsertTableBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}