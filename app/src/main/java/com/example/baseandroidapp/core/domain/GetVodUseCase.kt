package com.example.baseandroidapp.core.domain

import com.example.baseandroidapp.core.data.VodRepository
import com.example.baseandroidapp.core.domain.GetVodUseCase.Params
import com.example.baseandroidapp.core.exception.Failure
import com.example.baseandroidapp.core.functional.Either
import com.example.baseandroidapp.core.interactor.UseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetVodUseCase
@Inject constructor(
    private val repository: VodRepository
) : UseCase<Vod, Params>() {

    data class Params(val id:Int)

    override suspend fun run(params: Params): Either<Failure, Vod> = repository.getVod(params.id)
}

