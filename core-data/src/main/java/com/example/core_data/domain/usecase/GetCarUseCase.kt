package com.example.core_data.domain.usecase

import com.example.core_data.domain.repository.CarRepository
import com.example.core_data.domain.entity.Car
import com.example.core_data.domain.entity.ManufacturerType
import io.reactivex.Single
import javax.inject.Inject

interface GetCarUseCase {

    fun getCar(url: String, type: ManufacturerType): Single<Car>
}

class GetCarUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetCarUseCase {

    override fun getCar(url: String, type: ManufacturerType): Single<Car> {
        return carRepository.getCar(url, type)
    }

}