package com.example.core_data.domain.usecase

import com.example.core_data.domain.repository.CarRepository
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_data.domain.entity.PartsData
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