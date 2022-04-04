package com.example.baseandroidapp.core.domain

import com.example.baseandroidapp.core.data.VodRepository
import com.example.baseandroidapp.core.exception.Failure
import com.example.baseandroidapp.core.functional.Either
import com.example.baseandroidapp.core.interactor.UseCase
import javax.inject.Inject

class GetVodListUseCase
    @Inject constructor(val repository: VodRepository) : UseCase<List<Vod>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<Vod>> = repository.getVodList()
}