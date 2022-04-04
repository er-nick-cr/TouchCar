package com.example.touchcar.presentation.choose_model

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Model
import com.example.touchcar.domain.usecase.GetModelsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.RuntimeException
import javax.inject.Inject

class ChooseModelViewModel @Inject constructor(
    private val getModelsUseCase: GetModelsUseCase
) : ViewModel() {

    val modelLiveData: MutableLiveData<List<Model>> = MutableLiveData<List<Model>>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getModels(url: String) {
        disposable.add(
            getModelsUseCase.getModels(url)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value -> modelLiveData.postValue(value) },
                    { error -> error.printStackTrace()})
        )
    }

    fun searchModel(models: List<Model>, s: Editable): List<Model> {
        return models.filter {
            it.modelName.contains(
                s.toString(),
                ignoreCase = true
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}