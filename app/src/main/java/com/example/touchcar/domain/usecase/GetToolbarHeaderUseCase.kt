package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.ToolbarHeader
import com.example.touchcar.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetToolbarHeaderUseCase {

    fun getToolbarHeader(url: String): Single<ToolbarHeader>
}

class GetToolbarHeaderUseCaseImpl @Inject constructor(
    val carRepository: CarRepository
) : GetToolbarHeaderUseCase {

    override fun getToolbarHeader(url: String): Single<ToolbarHeader> {
        return carRepository.getToolbar(url)
    }
}