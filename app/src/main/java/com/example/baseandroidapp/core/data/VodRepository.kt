package com.example.baseandroidapp.core.data

import com.example.baseandroidapp.core.domain.Vod
import io.reactivex.rxjava3.core.Single

interface VodRepository {

//    fun getVod(request: VodRequest): Single<VodResponse>
//    fun getVodList(): Single<VodListResponse>

//    fun getVod(request: VodRequest): Vod
//    fun getVodList(): List<Vod>

    fun getVod(request: VodRequest): Single<Vod>
    fun getVodList(): Single<List<Vod>>
}