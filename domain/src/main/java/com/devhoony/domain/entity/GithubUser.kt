package com.devhoony.domain.entity

data class GithubUser(
    val id: Int,
    val name: String,
    val imageUrl: String = "",
){
    companion object {
        val empty = GithubUser(0, "")
    }
}