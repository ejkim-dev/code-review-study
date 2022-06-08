package com.devhoony.domain.entity

data class Vod(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String = "",
    val videoUrl: String = "",
    val regDate: String = "",
    val viewCount: Int = 0
){
    companion object {
        val empty = Vod(0, "","")
    }
}