package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Model
import com.example.touchcar.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetModelsFromNetworkUseCase {
    fun getModels(url: String): Single<List<Model>>
}

class GetModelsFromNetworkUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
    ) : GetModelsFromNetworkUseCase {

    override fun getModels(url: String): Single<List<Model>> {
        return carRepository.getModels(url)
    }
}