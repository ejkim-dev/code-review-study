package com.devhoony.domain.repository

import com.devhoony.domain.entity.Vod
import com.devhoony.domain.util.exception.Failure
import com.devhoony.domain.util.functional.Either


interface VodRepository {

    suspend fun getVod(id: Int): Either<Failure, Vod>
    suspend fun getVodList(): Either<Failure, List<Vod>>

//    fun getVodDefer(request: VodRequest): Deferred<Vod>
//    fun getVodListDefer(): Deferred<List<Vod>>
}