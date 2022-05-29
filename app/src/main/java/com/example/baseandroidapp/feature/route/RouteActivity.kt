package com.example.baseandroidapp.feature.route

import android.os.Bundle
import android.view.LayoutInflater
import com.example.baseandroidapp.databinding.ActivityInitBinding
import com.example.baseandroidapp.util.base.ViewBindingActivity
import com.example.baseandroidapp.util.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RouteActivity : ViewBindingActivity<ActivityInitBinding>() {

    @Inject
    internal lateinit var navigator: Navigator

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityInitBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            btnSetting.setOnClickListener {
                navigator.showSetting(this@RouteActivity)
            }

            btnRead.setOnClickListener {
                navigator.showViewTable(this@RouteActivity)
            }

            btnInsert.setOnClickListener {
                navigator.showInsertTable(this@RouteActivity)
            }
        }

    }


}