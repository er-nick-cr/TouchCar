package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetBodyListUseCase {

    fun getBodyList(url: String): Single<List<Body>>
}

class GetBodyListUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetBodyListUseCase {
    override fun getBodyList(url: String): Single<List<Body>> {
        return carRepository.getBodyList(url)
    }
}