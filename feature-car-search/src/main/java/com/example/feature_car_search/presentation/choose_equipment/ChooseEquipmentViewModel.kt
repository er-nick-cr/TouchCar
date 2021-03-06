package com.example.feature_car_search.presentation.choose_equipment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_data.domain.entity.Equipment
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_data.domain.usecase.GetEquipmentUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChooseEquipmentViewModel @Inject constructor(
    private val getEquipmentUseCase: GetEquipmentUseCase,
    private val equipmentMapper: EquipmentMapper,
) : ViewModel() {

    val equipmentLiveData: MutableLiveData<List<Equipment>> = MutableLiveData<List<Equipment>>()
    private var equipments: List<Equipment> = emptyList()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestEquipments(url: String, manufacturerType: ManufacturerType) {
        disposable.add(
            getEquipmentUseCase.getEquipment(url, manufacturerType)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        val mappedValue = equipmentMapper.mapEquipmentValues(value)
                        equipmentLiveData.postValue(mappedValue)
                        equipments = mappedValue
                    },
                    { error -> error.printStackTrace() }
                )
        )
    }



    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}