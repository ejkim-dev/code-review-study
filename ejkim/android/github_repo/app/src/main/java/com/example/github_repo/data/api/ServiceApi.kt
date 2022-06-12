package com.example.github_repo.data.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**Todo 아직 추가중*/
interface ServiceApi {
    @Headers("Accept: application/vnd.github.v3.full+json",
        "User-Agent: Github-Repo-App")
    @GET("{path}")
    suspend fun getEntity(@Path("path", encoded = true) path: String)
}