package com.example.data.repositoryimpl


import com.example.data.DefaultResult
import retrofit2.Response
import javax.inject.Inject
import com.example.data.DefaultResult.SingleResult
import com.example.data.DefaultResult.PageResult
import com.example.data.DefaultResult.ListResult
import com.example.data.VodDetailEntity
import com.example.data.remote.NetworkHandler
import com.example.data.remote.VodService
import com.example.domain.repository.VodRepository
import com.example.domain.entity.Vod
import com.example.domain.util.exception.Failure
import com.example.domain.util.exception.Failure.ServerError
import com.example.domain.util.exception.Failure.NetworkConnection
import com.example.domain.util.functional.Either
import com.example.domain.util.functional.Either.Left
import com.example.domain.util.functional.Either.Right

class VodRepositoryImpl @Inject constructor(
//    private val localDataSource: VodLocalDataSource,
//    private val remoteDataSource: VodRemoteDataSource,
    private val vodService: VodService,
    private val networkHandler: NetworkHandler
) : VodRepository {

    private fun <T, L : DefaultResult<T>, R> request(
        response: Response<L>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            if (response.isSuccessful && (response.body()?.success ?: false)) {
                when (response.body()) {
                    is SingleResult<*> -> requestSingle(response as Response<SingleResult<T>>, transform, default)
                    is ListResult<*> -> requestList(response as Response<ListResult<T>>, transform, default)
                    is PageResult<*> -> requestPage(response as Response<PageResult<T>>, transform, default)
                    else -> Left(ServerError)
                }
            } else {
                Left(ServerError)
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






    override suspend fun getVod(id: Int): Either<Failure, Vod> {
        // check network status before network request
        return when (networkHandler.isNetworkAvailable()) {
            true ->  request(
                vodService.vod(id),
                { it.toVod() },
                VodDetailEntity.empty
            )
            false -> Left(NetworkConnection)
        }
    }

    override suspend fun getVodList(): Either<Failure, List<Vod>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(
                vodService.vodList(),
                { it.map { entity-> entity.toVod() } },
                emptyList()
            )
            false -> Left(NetworkConnection)
        }
    }

}