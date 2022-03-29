package com.example.baseandroidapp.core.data

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VodService
@Inject constructor(retrofit: Retrofit) : VodApi {
    private val vodApi by lazy { retrofit.create(VodApi::class.java) }

    override fun vodList(): Call<List<VodEntity>> = vodApi.vodList()

    override fun vodRx(id: Int): Single<VodResponse> = vodApi.vodRx(id)
    override fun vodListRx(): Single<VodListResponse> = vodApi.vodListRx()
}