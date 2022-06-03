package com.example.baseandroidapp.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import com.example.baseandroidapp.databinding.ActivityLoginBinding
import com.example.baseandroidapp.util.base.ViewBindingActivity
import com.example.baseandroidapp.util.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ViewBindingActivity<ActivityLoginBinding>() {

    @Inject
    lateinit var navigator: Navigator

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityLoginBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}