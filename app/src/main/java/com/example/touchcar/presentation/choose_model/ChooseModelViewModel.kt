package com.example.touchcar.presentation.choose_model

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Model
import com.example.touchcar.domain.usecase.GetModelsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChooseModelViewModel @Inject constructor(
    private val getModelsUseCase: GetModelsUseCase
) : ViewModel() {

    val modelLiveData: MutableLiveData<List<Model>> = MutableLiveData<List<Model>>()
    private var models: List<Model> = emptyList()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestModels(url: String) {
        disposable.add(
            getModelsUseCase.getModels(url)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        modelLiveData.postValue(value)
                        models = value
                    },
                    { error -> error.printStackTrace()})
        )
    }

    fun searchModel(searchValue: String) {
        modelLiveData.postValue(models.filter {
            it.modelName.contains(
                searchValue,
                ignoreCase = true
            )
        })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}