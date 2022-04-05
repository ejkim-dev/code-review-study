package com.example.domain.repository

import com.example.domain.entity.Vod
import com.example.domain.util.exception.Failure
import com.example.domain.util.functional.Either


interface VodRepository {

    suspend fun getVod(id: Int): Either<Failure, Vod>
    suspend fun getVodList(): Either<Failure, List<Vod>>

//    fun getVodDefer(request: VodRequest): Deferred<Vod>
//    fun getVodListDefer(): Deferred<List<Vod>>
}