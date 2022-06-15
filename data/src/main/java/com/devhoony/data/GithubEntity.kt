package com.devhoony.data

import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser

data class GithubUserEntity(
    val id: Int = 0,
    val login: String = "",
    val avatar_url: String = "",
)
//{
//    companion object {
//        val empty = GithubUserEntity(0, "", "")
//    }
//}


data class GithubReposEntity(
    val id: Int,
    val name: String,
    val description: String,
    val stargazers_count: Int,
)