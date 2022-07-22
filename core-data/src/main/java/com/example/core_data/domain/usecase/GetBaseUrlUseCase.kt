package com.example.core_data.domain.usecase

import com.example.core_data.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetBaseUrlUseCase {

    fun getBaseUrl(url: String): Single<String>
}

internal class GetBaseUrlUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetBaseUrlUseCase {

    override fun getBaseUrl(url: String): Single<String> {
        return carRepository.getBaseUrl(url)
    }
}