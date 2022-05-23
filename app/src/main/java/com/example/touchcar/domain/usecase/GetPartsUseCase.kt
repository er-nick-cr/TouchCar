package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.entity.Part
import com.example.touchcar.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetPartsUseCase {

    fun getParts(url: String, type: ManufacturerType): Single<List<Part>>
}

class GetPartsUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetPartsUseCase {

    override fun getParts(url: String, type: ManufacturerType): Single<List<Part>> {
        return carRepository.getParts(url, type)
    }
}