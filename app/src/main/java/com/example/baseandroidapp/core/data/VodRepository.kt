package com.example.baseandroidapp.core.data

import com.example.baseandroidapp.core.domain.Vod
import com.example.baseandroidapp.core.exception.Failure
import com.example.baseandroidapp.core.functional.Either
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred

interface VodRepository {

    suspend fun getVod(id: Int): Either<Failure, Vod>
    suspend fun getVodList(): Either<Failure, List<Vod>>

    fun getVodRx(request: VodRequest): Single<Vod>
    fun getVodListRx(): Single<List<Vod>>

    suspend fun getVodCo(request: VodRequest): Vod
    suspend fun getVodListCo(): List<Vod>

//    fun getVodDefer(request: VodRequest): Deferred<Vod>
//    fun getVodListDefer(): Deferred<List<Vod>>
}