package com.devhoony.data.remote.github

import com.devhoony.data.GithubReposEntity
import com.devhoony.data.GithubUserEntity
import kotlinx.coroutines.flow.Flow
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



    // Coroutine Flow
    @GET("/users/{userName}")
    suspend fun getUserInfoFlow(
        @Path(value = "userName", encoded = true) userName:String
    ): Flow<GithubUserEntity>

    @GET("/users/{userName}/repos")
    suspend fun getUserRepoInfoFlow(
        @Path(value = "userName", encoded = true) userName:String
    ): Flow<List<GithubReposEntity>>


    @GET("/users/{userName}")
    suspend fun getUserInfoFlow2(
        @Path(value = "userName", encoded = true) userName:String
    ): GithubUserEntity

    @GET("/users/{userName}/repos")
    suspend fun getUserRepoInfoFlow2(
        @Path(value = "userName", encoded = true) userName:String
    ): List<GithubReposEntity>

}