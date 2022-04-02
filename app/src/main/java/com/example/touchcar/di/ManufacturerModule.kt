package com.example.touchcar.di

import com.example.touchcar.TouchCarApplication
import com.example.touchcar.data.repository.ManufacturerRepositoryImpl
import com.example.touchcar.data.repository.ManufacturerRepositoryImpl_Factory
import com.example.touchcar.domain.repository.ManufacturerRepository
import com.example.touchcar.domain.usecase.GetManufacturerFromNetworkUseCase
import com.example.touchcar.domain.usecase.GetManufacturerFromNetworkUseCaseImpl
import com.example.touchcar.presentation.MainActivity
import com.example.touchcar.presentation.TouchCarNavigator
import com.example.touchcar.presentation.main_menu.MainMenuFragment
import com.example.touchcar.presentation.main_menu.MainMenuViewModel
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
    abstract fun bindManufacturerRepository(manufacturerRepositoryImpl: ManufacturerRepositoryImpl): ManufacturerRepository

    @Binds
    abstract fun bindGetManufacturerFromNetworkUseCase(getManufacturerFromNetworkUseCaseImpl: GetManufacturerFromNetworkUseCaseImpl) : GetManufacturerFromNetworkUseCase
}