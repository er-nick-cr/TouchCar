package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.repository.CarRepository
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