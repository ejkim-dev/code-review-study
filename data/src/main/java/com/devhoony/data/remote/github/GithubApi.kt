package com.devhoony.data.remote.github

import com.devhoony.data.GithubReposEntity
import com.devhoony.data.GithubUserEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users/{userName}")
    suspend fun getUserInfo(
        @Path(value = "userName", encoded = true) userName:String
    ): Response<GithubUserEntity>

    @GET("/users/{userName}/repos")
    suspend fun getUserRepoInfo(
        @Path(value = "userName", encoded = true) userName:String
    ): Response<List<GithubReposEntity>>

}