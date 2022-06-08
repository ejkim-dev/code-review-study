package com.devhoony.data.remote.github

import com.devhoony.data.GithubReposEntity
import com.devhoony.data.GithubUserEntity
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubService
@Inject constructor(retrofit: Retrofit) : GithubApi {

    private val githubApi by lazy { retrofit.create(GithubApi::class.java) }

    override suspend fun getUserInfo(userName: String): Response<GithubUserEntity> {
        return githubApi.getUserInfo(userName)
    }

    override suspend fun getUserRepoInfo(userName: String): Response<List<GithubReposEntity>> {
        return githubApi.getUserRepoInfo(userName)
    }
}