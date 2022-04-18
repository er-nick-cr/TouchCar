package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetEquipmentUseCase {

    fun getEquipment(url: String, manufacturerType: ManufacturerType): Single<List<Equipment>>
}

class GetEquipmentUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetEquipmentUseCase {
    override fun getEquipment(url: String, manufacturerType: ManufacturerType): Single<List<Equipment>> {
        return carRepository.getEquipment(url, manufacturerType)
    }

}