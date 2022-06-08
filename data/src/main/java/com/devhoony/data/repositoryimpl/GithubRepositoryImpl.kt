package com.devhoony.data.repositoryimpl


import com.devhoony.data.DefaultResult
import retrofit2.Response
import javax.inject.Inject
import com.devhoony.data.DefaultResult.SingleResult
import com.devhoony.data.DefaultResult.PageResult
import com.devhoony.data.DefaultResult.ListResult
import com.devhoony.data.GithubUserEntity
import com.devhoony.data.VodDetailEntity
import com.devhoony.data.remote.NetworkHandler
import com.devhoony.data.remote.VodService
import com.devhoony.data.remote.github.GithubService
import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.entity.GithubUser.Companion.empty
import com.devhoony.domain.repository.VodRepository
import com.devhoony.domain.entity.Vod
import com.devhoony.domain.repository.GithubRepository
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.exception.Failure.ServerError
import com.devhoony.domain.util.exception.Failure.NetworkConnection
import com.devhoony.domain.util.functional.Either
import com.devhoony.domain.util.functional.Either.Left
import com.devhoony.domain.util.functional.Either.Right

class GithubRepositoryImpl @Inject constructor(
//    private val localDataSource: VodLocalDataSource,
//    private val remoteDataSource: VodRemoteDataSource,
    private val githubService: GithubService,
    private val networkHandler: NetworkHandler
) : GithubRepository {

//    private fun <T, L : DefaultResult<T>, R> request(
//        response: Response<L>,
//        transform: (T) -> R,
//        default: T
//    ): Either<Failure, R> {
//        return try {
//            if (response.isSuccessful && (response.body()?.success ?: false)) {
//                when (response.body()) {
//                    is SingleResult<*> -> requestSingle(response as Response<SingleResult<T>>, transform, default)
//                    is ListResult<*> -> requestList(response as Response<ListResult<T>>, transform, default)
//                    is PageResult<*> -> requestPage(response as Response<PageResult<T>>, transform, default)
//                    else -> Left(ServerError)
//                }
//            } else {
//                Left(ServerError)
//            }
//
//        } catch (exception: Throwable) {
//            Left(ServerError)
//        }
//    }

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



    private fun <T, R> requestSingle(
        response: Response<SingleResult<T>>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            when (response.isSuccessful && (response.body()?.success ?: false)) {
                true -> Right(transform((response.body()?.data ?: default)))
                false -> Left(ServerError)
            }
        } catch (exception: Throwable) {
            Left(ServerError)
        }
    }

    private fun <T, R> requestList(
        response: Response<ListResult<T>>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            when (response.isSuccessful && (response.body()?.success ?: false)) {
                true -> Right(transform((response.body()?.list ?: default)))
                false -> Left(ServerError)
            }
        } catch (exception: Throwable) {
            Left(ServerError)
        }
    }

    private fun <T, R> requestPage(
        response: Response<PageResult<T>>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            when (response.isSuccessful && (response.body()?.success ?: false)) {
                true -> Right(transform((response.body()?.page?.content ?: default)))
                false -> Left(ServerError)
            }
        } catch (exception: Throwable) {
            Left(ServerError)
        }
    }



    override suspend fun getUser(userName: String): Either<Failure, GithubUser> {
        return when (networkHandler.isNetworkAvailable()) {
            true ->  request(
                githubService.getUserInfo(userName),
                { it.toGithubUser() },
                GithubUserEntity.empty
            )
            false -> Left(NetworkConnection)
        }
    }

    override suspend fun getRepos(userName: String): Either<Failure, List<GithubRepo>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(
                githubService.getUserRepoInfo(userName),
                { it.map { entity-> entity.toGithubRepo() } },
                emptyList()
            )
            false -> Left(NetworkConnection)
        }
    }

}