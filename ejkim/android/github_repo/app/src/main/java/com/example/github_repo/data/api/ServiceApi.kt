package com.example.github_repo.data.api

import com.example.github_repo.data.entity.HttpResponseEntity
import com.example.github_repo.data.entity.UserInfo
import com.example.github_repo.data.entity.UserRepos
import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ServiceApi {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("{path}")
    suspend fun getEntity(@Path("path", encoded = true) path: String): Single<JsonElement>


    //따로 쓸지?
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/users/{username}")
    suspend fun fetchUserInfo(@Path ("username") username: String): UserInfo

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/users/{username}/repos")
    suspend fun fetchUserRepos(@Path ("username") username: String): UserRepos
}