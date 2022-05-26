package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.entity.Part
import com.example.touchcar.domain.entity.PartsData
import com.example.touchcar.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetPartsDataUseCase {

    fun getPartsData(url: String, type: ManufacturerType): Single<PartsData>
}

class GetPartsDataUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetPartsDataUseCase {

    override fun getPartsData(url: String, type: ManufacturerType): Single<PartsData> {
        return carRepository.getPartsData(url, type)
    }
}