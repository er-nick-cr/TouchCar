package com.example.core_data.domain.usecase

import com.example.core_data.domain.entity.Component
import com.example.core_data.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetComponentUseCase {

    fun getComponent(url: String, baseUrl: String, innerUrl: String): Single<Component>
}

class GetComponentUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetComponentUseCase {

    override fun getComponent(url: String, baseUrl: String, innerUrl: String): Single<Component> {
        return carRepository.getComponent(url, baseUrl, innerUrl)
    }
}