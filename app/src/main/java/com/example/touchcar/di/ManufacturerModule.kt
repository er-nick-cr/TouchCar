package com.example.touchcar.di

import com.example.touchcar.data.repository.CarRepositoryImpl
import com.example.touchcar.domain.repository.CarRepository
import com.example.touchcar.domain.usecase.GetManufacturerFromNetworkUseCase
import com.example.touchcar.domain.usecase.GetManufacturerFromNetworkUseCaseImpl
import com.example.touchcar.domain.usecase.GetModelsFromNetworkUseCase
import com.example.touchcar.domain.usecase.GetModelsFromNetworkUseCaseImpl
import com.example.touchcar.presentation.main_menu.MainMenuFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ManufacturerModule {

    @Binds
    abstract fun bindTouchCarNavigator(mainMenuFragment: MainMenuFragment): MainMenuFragment

    @Binds
    abstract fun bindManufacturerRepository(carRepositoryImpl: CarRepositoryImpl): CarRepository

    @Binds
    abstract fun bindGetManufacturerFromNetworkUseCase(getManufacturerFromNetworkUseCaseImpl: GetManufacturerFromNetworkUseCaseImpl): GetManufacturerFromNetworkUseCase

    @Binds
    abstract fun bindGetModelFromNetworkUseCase(getModelsFromNetworkUseCaseImpl: GetModelsFromNetworkUseCaseImpl): GetModelsFromNetworkUseCase
}