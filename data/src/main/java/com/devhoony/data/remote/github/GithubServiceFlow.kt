package com.devhoony.data.remote.github

import com.devhoony.data.GithubReposEntity
import com.devhoony.data.GithubUserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubServiceFlow @Inject constructor(retrofit: Retrofit) {

    private val githubApi by lazy { retrofit.create(GithubApi::class.java) }

    suspend fun getUserInfoFlow(userName: String): Flow<GithubUserEntity> {
        return flow {
//            githubApi.getUserInfoFlow2(userName)
            emit(githubApi.getUserInfoFlow2(userName))
        }
    }

    suspend fun getUserRepoInfoFlow(userName: String): Flow<List<GithubReposEntity>> {
        return flow {
//            githubApi.getUserRepoInfoFlow2(userName)
            emit(githubApi.getUserRepoInfoFlow2(userName))
        }
    }

}
