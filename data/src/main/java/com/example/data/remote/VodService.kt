package com.example.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton
import com.example.data.DefaultResult.SingleResult
import com.example.data.DefaultResult.PageResult
import com.example.data.VodDetailEntity
import com.example.data.VodEntity

@Singleton
class VodService
@Inject constructor(retrofit: Retrofit) : VodApi {
    private val vodApi by lazy { retrofit.create(VodApi::class.java) }

    override suspend fun vod(id: Int): Response<SingleResult<VodDetailEntity>> = vodApi.vod(id)
    override suspend fun vodList(): Response<PageResult<List<VodEntity>>> = vodApi.vodList()

//    override fun vodDefer(id: Int): Deferred<VodResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override fun vodListDefer(): Deferred<VodListResponse> {
//        TODO("Not yet implemented")
//    }
}