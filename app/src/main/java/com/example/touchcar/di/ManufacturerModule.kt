package com.example.touchcar.di

import com.example.touchcar.data.repository.CarRepositoryImpl
import com.example.touchcar.domain.repository.CarRepository
import com.example.touchcar.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ManufacturerModule {

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
    abstract fun bindGetPartsUseCase(getPartsUseCaseImpl: GetPartsUseCaseImpl): GetPartsUseCase

    @Binds
    abstract fun bindGetToolbarHeaderUseCase(getToolbarHeaderUseCaseImpl: GetToolbarHeaderUseCaseImpl): GetToolbarHeaderUseCase
}