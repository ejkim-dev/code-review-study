package com.devhoony.baseandroidapp.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.devhoony.baseandroidapp.databinding.ActivityMainBinding
import com.devhoony.baseandroidapp.util.DLog
import com.devhoony.baseandroidapp.util.base.ViewBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    companion object {
        fun callingIntent(context: Context) =
            context.startActivity(Intent(context, MainActivity::class.java))
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()

//        val userName = "dev-hoony"
//        CoroutineScope(Dispatchers.IO).launch {
//            viewModel.loadUserData(userName)
//            viewModel.loadGithubRepos(userName)
//        }


        DLog.e("intent : $intent")

        val data = intent.data
        DLog.e("data : $data")
        data?.let {
            val name = it.toString().replace("testapp://test.com/", "")
            DLog.e("userName : $name")
            if (!name.isNullOrEmpty()) {
                viewModel.loadUserData(name)
                viewModel.loadGithubRepos(name)
            }
        }

    }

    private fun initObserver() {
        viewModel.userData.observe(this) {
            DLog.e(it.toString())
            binding.tvUser.text = it.toString()
        }

        viewModel.reposData.observe(this) {
            binding.tvRepos.text = it.toString()
        }

        viewModel.failure.observe(this) {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
    }

}