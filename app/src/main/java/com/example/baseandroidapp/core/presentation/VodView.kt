package com.example.baseandroidapp.core.presentation

data class VodView(
    val id: Int,
    val title: String,
    val imageUrl: String,
)

data class VodDetailView(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
)