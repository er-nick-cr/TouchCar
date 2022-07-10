package com.example.core_data.domain.usecase

import com.example.core_data.domain.entity.Component
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_data.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetComponentUseCase {

    fun getComponent(url: String, baseUrl: String, innerUrl: String, type: ManufacturerType): Single<List<Component>>
}

class GetComponentUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetComponentUseCase {

    override fun getComponent(url: String, baseUrl: String, innerUrl: String, type: ManufacturerType): Single<List<Component>> {
        return carRepository.getComponent(url, baseUrl, innerUrl, type)
    }
}