package com.example.core_data.domain.usecase

import com.example.core_data.domain.entity.DetailedPart
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_data.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetDetailedPartUseCase {

    fun getDetailedPart(url: String, type: ManufacturerType): Single<DetailedPart>
}

class GetDetailedPartUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetDetailedPartUseCase {

    override fun getDetailedPart(url: String, type: ManufacturerType): Single<DetailedPart> {
       return carRepository.getDetailedPart(url, type)
    }
}