package com.example.touchcar.di

import com.example.touchcar.data.datasource.network.network_service.parsers.equipment.*
import com.example.touchcar.data.repository.CarRepositoryImpl
import com.example.touchcar.domain.repository.CarRepository
import com.example.touchcar.domain.usecase.*
import com.example.touchcar.presentation.MainMenuActivity
import com.example.touchcar.presentation.main_menu.MainMenuFragment
import com.example.touchcar.presentation.navigation.MainMenuNavigator
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
    abstract fun bindHondaEquipmentParser(hondaEquipmentParser: HondaEquipmentParser): EquipmentParser

    @Binds
    abstract fun bindLexusEquipmentParser(lexusEquipmentParser: LexusEquipmentParser): EquipmentParser

    @Binds
    abstract fun bindMazdaEquipmentParser(mazdaEquipmentParser: MazdaEquipmentParser): EquipmentParser

    @Binds
    abstract fun bindMitsubishiEquipmentParser(mitsubishiEquipmentParser: MitsubishiEquipmentParser): EquipmentParser

    @Binds
    abstract fun bindNissanEquipmentParser(nissanEquipmentParser: NissanEquipmentParser): EquipmentParser

    @Binds
    abstract fun bindSubaruEquipmentParser(subaruEquipmentParser: SubaruEquipmentParser): EquipmentParser

    @Binds
    abstract fun bindSuzukiEquipmentParser(suzukiEquipmentParser: SuzukiEquipmentParser): EquipmentParser

    @Binds
    abstract fun bindToyotaEquipmentParser(toyotaEquipmentParser: ToyotaEquipmentParser): EquipmentParser
}