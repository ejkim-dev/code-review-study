package com.example.baseandroidapp.util.navigator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RouteActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigator: Navigator


    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        recyclerView = findViewById(R.id.rv_route)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RouteRecyclerAdapter(this)

//        navigator.showNavigation(context = this)

    }
}