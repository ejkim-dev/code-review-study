package com.devhoony.data

import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser

data class GithubUserEntity(
    val id: Int,
    val login: String,
    val avatar_url: String,
) {
    companion object {
        val empty = GithubUserEntity(0, "", "")
    }

    fun toGithubUser() = GithubUser(
        id = id,
        name = login,
        imageUrl = avatar_url
    )
}


data class GithubReposEntity(
    val id: Int,
    val name: String,
    val description: String,
    val stargazers_count: Int,
) {
    fun toGithubRepo() =
        GithubRepo(id = id, title = name, description = description, starCount = stargazers_count)
}