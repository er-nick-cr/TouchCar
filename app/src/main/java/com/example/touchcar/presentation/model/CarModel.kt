package com.example.touchcar.presentation.model

sealed class CarModel {

    object CarPartsHeading : CarModel()
    class CarInfoModel(val carInfo: CarInfo) : CarModel()
    class DetailsModel(val details: Details) : CarModel()
}