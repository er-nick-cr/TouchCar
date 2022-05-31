package com.example.core_data.domain.usecase

import com.example.core_data.domain.repository.CarRepository
import com.example.core_data.domain.entity.Model
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