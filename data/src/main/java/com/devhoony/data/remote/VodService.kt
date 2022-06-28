package com.devhoony.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton
import com.devhoony.data.DefaultResult.SingleResult
import com.devhoony.data.DefaultResult.PageResult
import com.devhoony.data.VodDetailEntity
import com.devhoony.data.VodEntity
import javax.inject.Named

@Singleton
class VodService
@Inject constructor(@Named("second") retrofit: Retrofit) : VodApi {
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