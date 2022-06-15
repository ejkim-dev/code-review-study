package com.devhoony.data.repositoryimpl


import android.util.Log
import com.devhoony.data.GithubUserEntity
import com.devhoony.data.mapper.GithubMapper
import com.devhoony.data.remote.NetworkHandler
import com.devhoony.data.remote.github.GithubService
import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.repository.GithubRepository
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.exception.Failure.NetworkConnection
import com.devhoony.domain.util.exception.Failure.ServerError
import com.devhoony.domain.util.functional.Either
import com.devhoony.domain.util.functional.Either.Left
import com.devhoony.domain.util.functional.Either.Right
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val mapper: GithubMapper,
    private val githubService: GithubService,
    private val networkHandler: NetworkHandler,
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
                {
                    Log.e("sdf", it.toString())
                    mapper.transform(it)
//                    mapper::transform(it)
                },
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
                { mapper.transform(it) },
                emptyList()
            )
            false -> Left(NetworkConnection)
        }
    }

}