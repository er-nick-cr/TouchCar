package com.example.core_data.domain.usecase

import com.example.core_data.domain.repository.CarRepository
import com.example.core_data.domain.entity.Equipment
import com.example.core_data.domain.entity.ManufacturerType
import io.reactivex.Single
import javax.inject.Inject

interface GetEquipmentUseCase {

    fun getEquipment(url: String, manufacturerType: ManufacturerType): Single<List<Equipment>>
}

internal class GetEquipmentUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetEquipmentUseCase {

    override fun getEquipment(url: String, manufacturerType: ManufacturerType): Single<List<Equipment>> {
        return carRepository.getEquipment(url, manufacturerType)
    }
}