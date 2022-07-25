package com.devhoony.data.repositoryimpl


import android.util.Log
import com.devhoony.data.GithubReposEntity
import com.devhoony.data.GithubUserEntity
import com.devhoony.data.mapper.GithubMapper
import com.devhoony.data.remote.NetworkHandler
import com.devhoony.data.remote.github.GithubService
import com.devhoony.data.remote.github.GithubServiceFlow
import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.repository.GithubRepository
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.exception.Failure.NetworkConnection
import com.devhoony.domain.util.exception.Failure.ServerError
import com.devhoony.domain.util.functional.Either
import com.devhoony.domain.util.functional.Either.Left
import com.devhoony.domain.util.functional.Either.Right
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val mapper: GithubMapper,
    private val githubService: GithubService,
    private val networkHandler: NetworkHandler,
    private val githubServiceFlow: GithubServiceFlow,
) : GithubRepository {

    private fun <T, R> request(
        response: Response<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            when (response.isSuccessful) {
                true -> Right(transform((response.body() ?: default)))
                false -> Left(ServerError)
            }
        } catch (exception: Throwable) {
            Left(ServerError)
        }
    }


    override suspend fun getUser(userName: String): Either<Failure, GithubUser> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(
                githubService.getUserInfo(userName),
                mapper::transform,
                GithubUserEntity()
            )
            false -> Left(NetworkConnection)
        }
    }

    override suspend fun getRepos(userName: String): Either<Failure, List<GithubRepo>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(
                githubService.getUserRepoInfo(userName),
//                { list -> list.map { mapper.transform(it) } },
                mapper::transform,
                emptyList()
            )
            false -> Left(NetworkConnection)
        }
    }

    override suspend fun getUserFlow(userName: String): Flow<GithubUser> {
//        return flow<GithubUserEntity> {
//            githubService.getUserInfoFlow(userName)
//        }.map(mapper::transform)

        //flow2 test
        return githubServiceFlow.getUserInfoFlow(userName).map {
            mapper.transform(it)
        }

//        return when(networkHandler.isNetworkAvailable()){
//            true->{githubService.getUserInfoFlow(userName)}
//            false->{}
//        }
    }

    override suspend fun getReposFlow(userName: String): Flow<List<GithubRepo>> {
//        return flow<List<GithubReposEntity>> {
//            githubService.getUserRepoInfoFlow(userName)
//        }.map(mapper::transform)

        //flow2 test
        return githubServiceFlow.getUserRepoInfoFlow(userName).map {
            mapper.transform(it)
        }


//        return githubService.getUserRepoInfoFlow(userName).map(mapper::transform)
    }

}