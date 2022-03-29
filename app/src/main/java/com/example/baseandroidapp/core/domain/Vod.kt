package com.example.baseandroidapp.core.domain

data class Vod(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String = "",
    val videoUrl: String = "",
    val regDate: String = "",
    val viewCount: Int = 0
)