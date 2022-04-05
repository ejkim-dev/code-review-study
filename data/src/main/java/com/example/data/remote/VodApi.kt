package com.example.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.data.DefaultResult.SingleResult
import com.example.data.DefaultResult.PageResult
import com.example.data.VodDetailEntity
import com.example.data.VodEntity

interface VodApi {
    companion object {
        private const val GET_VOD = "v1/tutorials/{id}"
        private const val GET_VOD_LIST = "v1/tutorials"
    }

    // Kotlin Coroutines - 1. suspend 사용
    @GET(GET_VOD)
    suspend fun vod(
        @Path(value = "id", encoded = true) id:Int
    ): Response<SingleResult<VodDetailEntity>>

    @GET(GET_VOD_LIST)
    suspend fun vodList(): Response<PageResult<List<VodEntity>>>




//    // Kotlin Coroutines - 2. Deferred 사용
//    @GET(GET_VOD)
//    suspend fun vodDefer(
//        @Path(value = "id", encoded = true) id:Int
//    ): Deferred<VodResponse>
//
//    @GET(GET_VOD_LIST)
//    fun vodListDefer(): Deferred<VodListResponse>
}