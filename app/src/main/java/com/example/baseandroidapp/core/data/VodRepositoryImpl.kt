package com.example.baseandroidapp.core.data

import com.example.baseandroidapp.core.domain.Vod
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class VodRepositoryImpl @Inject constructor(
//    private val localDataSource: VodLocalDataSource,
//    private val remoteDataSource: VodRemoteDataSource,
    private val vodService: VodService
) : VodRepository {
    override fun getVod(request: VodRequest): Single<Vod> {
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

    override fun getVodList(): Single<List<Vod>> {
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