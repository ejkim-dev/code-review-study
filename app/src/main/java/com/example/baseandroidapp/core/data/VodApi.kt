package com.example.baseandroidapp.core.data

import io.reactivex.rxjava3.core.Single
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

    @GET(GET_VOD)
    fun vodRx(
        @Path(value = "id", encoded = true) id:Int
    ): Single<VodResponse>

    @GET(GET_VOD_LIST)
    fun vodListRx(): Single<VodListResponse>
}