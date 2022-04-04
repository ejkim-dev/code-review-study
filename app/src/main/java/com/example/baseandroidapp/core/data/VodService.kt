package com.example.baseandroidapp.core.data

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton
import com.example.baseandroidapp.core.data.DefaultRes.SingleResult
import com.example.baseandroidapp.core.data.DefaultRes.PageResult
import com.example.baseandroidapp.core.data.DefaultRes.ListResult

@Singleton
class VodService
@Inject constructor(retrofit: Retrofit) : VodApi {
    private val vodApi by lazy { retrofit.create(VodApi::class.java) }

//    override suspend fun vod(id: Int): Response<VodResponse> = vodApi.vod(id)
    override suspend fun vod(id: Int): Response<SingleResult<VodDetailEntity>> = vodApi.vod(id)
//    override suspend fun vodList(): Response<VodListResponse> = vodApi.vodList()
    override suspend fun vodList(): Response<PageResult<List<VodEntity>>> = vodApi.vodList()

    override fun vodRx(id: Int): Single<VodResponse> = vodApi.vodRx(id)
    override fun vodListRx(): Single<VodListResponse> = vodApi.vodListRx()


    override suspend fun vodCo(id: Int): VodResponse = vodApi.vodCo(id)
    override suspend fun vodListCo(): Response<VodListResponse> = vodApi.vodListCo()
//    override suspend fun vodListCo(): VodListResponse = vodApi.vodListCo()

//    override fun vodDefer(id: Int): Deferred<VodResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override fun vodListDefer(): Deferred<VodListResponse> {
//        TODO("Not yet implemented")
//    }
}