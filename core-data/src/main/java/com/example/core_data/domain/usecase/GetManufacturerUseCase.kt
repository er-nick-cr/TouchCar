package com.example.core_data.domain.usecase

import com.example.core_data.domain.repository.CarRepository
import com.example.core_data.domain.entity.Manufacturer
import io.reactivex.Single
import javax.inject.Inject

interface GetManufacturerUseCase {

    fun getManufacturer(): Single<List<Manufacturer>>
}

internal class GetManufacturerUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetManufacturerUseCase {

    override fun getManufacturer(): Single<List<Manufacturer>> {
        return carRepository.getManufacturers()
    }
}