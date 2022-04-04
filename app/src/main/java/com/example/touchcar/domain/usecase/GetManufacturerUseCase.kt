package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetManufacturerUseCase {

    fun getManufacturer(): Single<List<Manufacturer>>
}

class GetManufacturerUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetManufacturerUseCase {

    override fun getManufacturer(): Single<List<Manufacturer>> {
        return carRepository.getManufacturers()
    }
}