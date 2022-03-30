package com.example.baseandroidapp.core.data

import com.example.baseandroidapp.core.domain.Vod
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred

interface VodRepository {

    fun getVod(request: VodRequest): Single<Vod>
    fun getVodList(): Single<List<Vod>>

    suspend fun getVodCo(request: VodRequest): Vod
    suspend fun getVodListCo(): List<Vod>

//    fun getVodDefer(request: VodRequest): Deferred<Vod>
//    fun getVodListDefer(): Deferred<List<Vod>>
}