package com.example.touchcar.presentation.choose_equipment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.usecase.GetBodyListUseCase
import com.example.touchcar.domain.usecase.GetEquipmentUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChooseEquipmentViewModel @Inject constructor(
    private val getEquipmentUseCase: GetEquipmentUseCase
) : ViewModel() {

    val equipmentLiveData: MutableLiveData<List<Equipment>> = MutableLiveData<List<Equipment>>()
    val equipments: List<Equipment> = emptyList()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestEquipments(url: String) {
        disposable.add(
            getEquipmentUseCase.getEquipment(url)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {value ->
                        equipmentLiveData.postValue(value)
                    }
                )
        )
    }
}