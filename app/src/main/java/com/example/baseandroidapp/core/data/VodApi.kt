package com.example.baseandroidapp.core.data

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VodApi {
    companion object {
        private const val GET_VOD = "v1/tutorials/{id}"
        private const val GET_VOD_LIST = "v1/tutorials"
    }

    @GET(GET_VOD_LIST)
    fun vodList(): Call<List<VodEntity>>


    // ReactiveX
    @GET(GET_VOD)
    fun vodRx(
        @Path(value = "id", encoded = true) id:Int
    ): Single<VodResponse>

    @GET(GET_VOD_LIST)
    fun vodListRx(): Single<VodListResponse>

    // Kotlin Coroutines - 1. suspend 사용
    @GET(GET_VOD)
    suspend fun vodCo(
        @Path(value = "id", encoded = true) id:Int
    ): VodResponse

    @GET(GET_VOD_LIST)
    suspend fun vodListCo(): VodListResponse

//    // Kotlin Coroutines - 2. Deferred 사용
//    @GET(GET_VOD)
//    suspend fun vodDefer(
//        @Path(value = "id", encoded = true) id:Int
//    ): Deferred<VodResponse>
//
//    @GET(GET_VOD_LIST)
//    fun vodListDefer(): Deferred<VodListResponse>
}