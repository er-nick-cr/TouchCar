package com.example.core_data.di

import com.example.core_data.domain.repository.CarRepository
import com.example.core_data.domain.repository.CarRepositoryImpl
import com.example.core_data.domain.usecase.*
import com.example.touchcar.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface DataModule {

    @Binds
    abstract fun bindManufacturerRepository(carRepositoryImpl: CarRepositoryImpl): CarRepository

    @Binds
    abstract fun bindGetManufacturerUseCase(getManufacturerFromNetworkUseCaseImpl: GetManufacturerUseCaseImpl): GetManufacturerUseCase

    @Binds
    abstract fun bindGetBodyListUseCase(getBodyListUseCaseImpl: GetBodyListUseCaseImpl): GetBodyListUseCase

    @Binds
    abstract fun bindGetModelUseCase(getModelsFromNetworkUseCaseImpl: GetModelsUseCaseImpl): GetModelsUseCase

    @Binds
    abstract fun bindGetEquipmentUseCase(getEquipmentUseCaseImpl: GetEquipmentUseCaseImpl): GetEquipmentUseCase

    @Binds
    abstract fun bindGetCarUseCase(getCarUseCaseImpl: GetCarUseCaseImpl): GetCarUseCase

    @Binds
    abstract fun bindGetPartsDataUseCase(getPartsDataUseCaseImpl: GetPartsDataUseCaseImpl): GetPartsDataUseCase
}