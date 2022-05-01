package com.example.touchcar.presentation.choose_equipment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.entity.Parameter
import com.example.touchcar.domain.usecase.GetEquipmentUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChooseEquipmentViewModel @Inject constructor(
    private val getEquipmentUseCase: GetEquipmentUseCase
) : ViewModel() {

    val equipmentLiveData: MutableLiveData<List<Equipment>> = MutableLiveData<List<Equipment>>()
    private var equipments: List<Equipment> = emptyList()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestEquipments(url: String, manufacturerType: ManufacturerType) {
        disposable.add(
            getEquipmentUseCase.getEquipment(url, manufacturerType)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {value ->
                        val mappedValue = value.map { equipment -> mapEquipmentValues(equipment) }
                        equipmentLiveData.postValue(mappedValue)
                        equipments = mappedValue
                    },
                    { error -> error.printStackTrace()}
                )
        )
    }

    private fun mapEquipmentValues(equipment: Equipment): Equipment {
        equipment.parameters.map { parameter ->
            if (parameter.parameterValue.isBlank()) {
                parameter.parameterValue = "Нет"
            } else if (parameter.parameterValue == "*") {
                parameter.parameterValue = "Да"
            }
        }
        return equipment
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}