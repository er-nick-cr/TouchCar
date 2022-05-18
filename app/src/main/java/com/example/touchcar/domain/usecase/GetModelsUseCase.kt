package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.Model
import com.example.touchcar.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetModelsUseCase {
    fun getModels(url: String): Single<List<Model>>
}

class GetModelsUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
    ) : GetModelsUseCase {

    override fun getModels(url: String): Single<List<Model>> {
        return carRepository.getModels(url)
    }
}