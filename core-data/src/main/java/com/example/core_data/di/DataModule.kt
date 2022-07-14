package com.example.core_data.di

import com.example.core_data.domain.repository.CarRepository
import com.example.core_data.data.repository.CarRepositoryImpl
import com.example.core_data.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface DataModule {

    @Binds
    fun bindManufacturerRepository(carRepositoryImpl: CarRepositoryImpl): CarRepository

    @Binds
    fun bindGetManufacturerUseCase(getManufacturerFromNetworkUseCaseImpl: GetManufacturerUseCaseImpl): GetManufacturerUseCase

    @Binds
    fun bindGetBodyListUseCase(getBodyListUseCaseImpl: GetBodyListUseCaseImpl): GetBodyListUseCase

    @Binds
    fun bindGetModelUseCase(getModelsFromNetworkUseCaseImpl: GetModelsUseCaseImpl): GetModelsUseCase

    @Binds
    fun bindGetEquipmentUseCase(getEquipmentUseCaseImpl: GetEquipmentUseCaseImpl): GetEquipmentUseCase

    @Binds
    fun bindGetCarUseCase(getCarUseCaseImpl: GetCarUseCaseImpl): GetCarUseCase

    @Binds
    fun bindGetPartsDataUseCase(getPartsDataUseCaseImpl: GetPartsDataUseCaseImpl): GetPartsDataUseCase

    @Binds
    fun bindGetComponentUseCase(getComponentUseCaseImpl: GetComponentUseCaseImpl): GetComponentUseCase

    @Binds
    fun bindGetDetailedPartUseCase(getDetailedPartUseCaseImpl: GetDetailedPartUseCaseImpl): GetDetailedPartUseCase
}