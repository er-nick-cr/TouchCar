package com.example.core_data.domain.usecase

import com.example.core_data.domain.repository.CarRepository
import com.example.core_data.domain.entity.Body
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