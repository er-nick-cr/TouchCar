package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.repository.ManufacturerRepository
import io.reactivex.Single
import javax.inject.Inject

class GetManufacturerFromNetworkUseCaseImpl @Inject constructor(val manufacturerRepository: ManufacturerRepository) : GetManufacturerFromNetworkUseCase {
    override fun getManufacturer(): Single<List<Manufacturer>> {
        return manufacturerRepository.getManufacturers()
    }
}