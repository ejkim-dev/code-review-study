package com.example.baseandroidapp.core.data

import com.example.baseandroidapp.core.domain.Vod
import com.example.baseandroidapp.core.exception.Failure
import com.example.baseandroidapp.core.exception.Failure.ServerError
import com.example.baseandroidapp.core.exception.Failure.NetworkConnection
import com.example.baseandroidapp.core.functional.Either
import com.example.baseandroidapp.util.DLog
import com.example.baseandroidapp.core.functional.Either.Left
import com.example.baseandroidapp.core.functional.Either.Right
import com.example.baseandroidapp.util.base.NetworkHandler
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject
import com.example.baseandroidapp.core.data.DefaultRes.SingleResult
import com.example.baseandroidapp.core.data.DefaultRes.PageResult
import com.example.baseandroidapp.core.data.DefaultRes.ListResult

class VodRepositoryImpl @Inject constructor(
//    private val localDataSource: VodLocalDataSource,
//    private val remoteDataSource: VodRemoteDataSource,
    private val vodService: VodService,
    private val networkHandler: NetworkHandler
) : VodRepository {

//    private fun <T, R> request(
//        response: Response<T>,
//        transform: (T) -> R,
//        default: T
//    ): Either<Failure, R> {
//        return try {
//            when (response.isSuccessful) {
//                true -> Right(transform((response.body() ?: default)))
//                false -> Left(ServerError)
//            }
//        } catch (exception: Throwable) {
//            Left(ServerError)
//        }
//    }

    private fun <T, L : DefaultRes<T>, R> request(
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
//        return requestPage(
//        return request(
//            vodService.vodList(),
//            { it ->
//                it.map { it.toVod() }
//            },
//            emptyList()
//        )
    }

    override fun getVodRx(request: VodRequest): Single<Vod> {
        return vodService.vodRx(request.id).map {
            val data = it.data
            Vod(
                id = data.id,
                title = data.streamUrl,
                description = data.teaserStartTime.toString(),
                imageUrl = data.thumbnailUrl
            )
        }
    }


    override fun getVodListRx(): Single<List<Vod>> {
        return vodService.vodListRx().map { response ->
            response.list.vodList.map {
                Vod(
                    id = it.id,
                    title = it.streamUrl,
                    description = it.teaserStartTime.toString(),
                    imageUrl = it.thumbnailUrl
                )
            }
        }
    }


    override suspend fun getVodCo(request: VodRequest): Vod {
        val data = vodService.vodCo(request.id).data
        return Vod(
            id = data.id,
            title = data.streamUrl,
            description = data.teaserStartTime.toString(),
            imageUrl = data.thumbnailUrl
        )
    }

    override suspend fun getVodListCo(): List<Vod> {

        val response = vodService.vodListCo()

        return if (response.isSuccessful) {
            DLog.e("response success - $response")
            val list = response.body()!!.list

            list.vodList.map {
                Vod(
                    id = it.id,
                    title = it.streamUrl,
                    description = it.teaserStartTime.toString(),
                    imageUrl = it.thumbnailUrl
                )
            }
        } else {
            emptyList()
        }

//        val list = vodService.vodListCo().list
//
//        return list.vodList.map {
//            Vod(
//                id = it.id,
//                title = it.streamUrl,
//                description = it.teaserStartTime.toString(),
//                imageUrl = it.thumbnailUrl
//            )
//        }
    }


//    override fun getVod(request: VodRequest): Vod {
//        TODO("Not yet implemented")
//    }
//
//    override fun getVodList(): List<Vod> {
//        val result :Single<List<Vod>> = vodService.vodListRx().map { it.list.vodList.map {  Vod(id = it.id, title = it.streamUrl, description = it.teaserStartTime.toString(), imageUrl = it.thumbnailUrl) } }
//    }

    //repository (Data) : Entity(data layer) -> Business Data(domain layer)
    //viewModel (Presentation) : Business Data(domain layer) -> ViewData(presentation layer)

//    override fun getVod(request: VodRequest): Single<VodResponse> {
//        return vodService.vodRx(request.id)
//    }
//
//    override fun getVodList(): Single<VodListResponse> {
//        return vodService.vodListRx().doOnSuccess {
//            it.list.vodList.map { Vod(id = it.id, title = it.streamUrl, description = it.teaserStartTime.toString(), imageUrl = it.thumbnailUrl) }
//        }
//    }

}