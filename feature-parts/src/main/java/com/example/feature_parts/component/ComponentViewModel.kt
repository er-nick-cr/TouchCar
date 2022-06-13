package com.example.feature_parts.component

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_common.NetworkSource
import com.example.core_data.domain.entity.Component
import com.example.core_data.domain.entity.ComponentImageSize
import com.example.core_data.domain.entity.Coordinates
import com.example.core_data.domain.entity.Item
import com.example.core_data.domain.usecase.GetComponentUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ComponentViewModel @Inject constructor(
    private val getComponentUseCase: GetComponentUseCase
) : ViewModel() {

    val componentLiveData: MutableLiveData<Component> = MutableLiveData<Component>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestComponent(url: String, baseUrl: String, innerUrl: String) {
        disposable.add(
            getComponentUseCase.getComponent(url, baseUrl, innerUrl)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        componentLiveData.postValue(value)
                    },
                    { error -> error.printStackTrace() }
                )
        )
    }

    fun convertCoordinates(resource: Bitmap, items: List<Item>, imageSize: ComponentImageSize) : List<Coordinates> {
        return items.map { item ->
            val resourceRatio: Float = resource.width.toFloat()/resource.height.toFloat()
            val imageSizeRatio: Float = imageSize.width/imageSize.height
            val ratio: Float = resourceRatio/imageSizeRatio
            Coordinates(
                x1 = item.coordinates.x1 * ratio,
                y1 = item.coordinates.y1 * ratio,
                x2 = item.coordinates.x2 * ratio,
                y2 = item.coordinates.y2 * ratio
            )
        }
    }
}