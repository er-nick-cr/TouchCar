package com.example.touchcar.presentation.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.presentation.model.CarInfo
import com.example.touchcar.presentation.model.CarModel
import com.example.touchcar.presentation.model.Details

class CarToCarModelsMapper {

    fun mapCarToCarModel(car: Car) : ArrayList<CarModel> {
        val carModels: ArrayList<CarModel> = ArrayList()
        val carInfo: CarModel.CarInfoModel = CarModel.CarInfoModel(CarInfo(car))
        val carDetails: List<CarModel.DetailsModel> = car.parts.map { part ->
            CarModel.DetailsModel(Details(part))
        }
        carModels.add(carInfo)
        carModels.add(CarModel.CarPartsHeading)
        carModels.addAll(carDetails)
        return carModels
    }
}