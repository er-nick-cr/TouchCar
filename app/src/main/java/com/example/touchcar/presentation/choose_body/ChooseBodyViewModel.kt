package com.example.touchcar.presentation.choose_body

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.usecase.GetBodyListUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChooseBodyViewModel @Inject constructor(
    private val getBodyListUseCase: GetBodyListUseCase
) : ViewModel() {

    val bodyLiveData: MutableLiveData<List<Body>> = MutableLiveData<List<Body>>()
    private var bodyList: List<Body> = emptyList()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getBodyList(url: String) {
        disposable.add(
            getBodyListUseCase.getBodyList(url)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        bodyLiveData.postValue(value)
                        bodyList = value
                    },
                    { error -> error.printStackTrace() }
                )
        )
    }

    fun searchMarket(s: Editable) {
        bodyLiveData.postValue(bodyList.filter {
            it.bodyName.contains(
                s.toString(),
                ignoreCase = true
            )
        })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}