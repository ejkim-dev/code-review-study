package com.devhoony.baseandroidapp.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devhoony.baseandroidapp.databinding.ActivityMainBinding
import com.devhoony.baseandroidapp.util.DLog
import com.devhoony.baseandroidapp.util.base.ViewBindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private val githubAdapter: GithubAdapter = GithubAdapter()

    companion object {
        fun callingIntent(context: Context) =
            context.startActivity(Intent(context, MainActivity::class.java))
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObserver()

        DLog.e("intent : $intent")
        val data = intent.data
        DLog.e("data : $data")
        if (data != null) {
            val name = data.toString().replace("testapp://test.com/", "")
            DLog.e("userName : $name")
            if (!name.isNullOrEmpty()) {
                viewModel.loadUserData(name)
                viewModel.loadGithubRepos(name)
            }
        } else {
            viewModel.loadUserData("dev-hoony")
            viewModel.loadGithubRepos("dev-hoony")
        }
    }

    private fun initView() {
        binding.rvGithubInfo.apply {
            adapter = githubAdapter
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }
    }

    private fun initObserver() {

        viewModel.githubZipData.observe(this){
            DLog.e("event observe")
            addData(it)
        }

//        viewModel.userData.observe(this) {
//            DLog.e(it.toString())
//            try {
//                githubInfoList[0] = GithubInfoView(userView = it)
//            } catch (e: IndexOutOfBoundsException) {
//                githubInfoList.add(GithubInfoView(userView = it))
//            }
//            addData()
//        }
//
//        viewModel.reposData.observe(this) { list ->
//            list.map {
//                githubInfoList.add(GithubInfoView(repoView = it))
//            }
//            addData()
//        }

        viewModel.failure.observe(this) {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addData(list: ArrayList<GithubInfoView>) {
        githubAdapter.addList(list)
    }

}