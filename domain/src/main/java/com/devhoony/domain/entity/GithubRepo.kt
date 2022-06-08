package com.devhoony.domain.entity

data class GithubRepo(
    val id: Int,
    val title: String,
    val description: String,
    val starCount: Int = 0
){
    companion object {
        val empty = GithubRepo(0, "","")
    }
}