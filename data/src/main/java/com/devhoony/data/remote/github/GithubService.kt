package com.devhoony.data.remote.github

import com.devhoony.data.GithubReposEntity
import com.devhoony.data.GithubUserEntity
import kotlinx.coroutines.flow.Flow
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



    override suspend fun getUserInfoFlow(userName: String): Flow<GithubUserEntity> {
        return githubApi.getUserInfoFlow(userName)
    }

    override suspend fun getUserRepoInfoFlow(userName: String): Flow<List<GithubReposEntity>> {
        return githubApi.getUserRepoInfoFlow(userName)
    }



    override suspend fun getUserInfoFlow2(userName: String): GithubUserEntity {
        return githubApi.getUserInfoFlow2(userName)
    }

    override suspend fun getUserRepoInfoFlow2(userName: String): List<GithubReposEntity> {
        return githubApi.getUserRepoInfoFlow2(userName)
    }

//    override suspend fun getUserInfoFlow(userName: String): GithubUserEntity {
//        return githubApi.getUserInfoFlow(userName)
//    }
//
//    override suspend fun getUserRepoInfoFlow(userName: String): List<GithubReposEntity> {
//        return githubApi.getUserRepoInfoFlow(userName)
//    }

//    override suspend fun getUserInfoFlow(userName: String): Flow<GithubUserEntity> {
//        return githubApi.getUserInfoFlow(userName)
//    }
//
//    override suspend fun getUserRepoInfoFlow(userName: String): List<GithubReposEntity>> {
//        return githubApi.getUserRepoInfoFlow(userName)
//    }

}