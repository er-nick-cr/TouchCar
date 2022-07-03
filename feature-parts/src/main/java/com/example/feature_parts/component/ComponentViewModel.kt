package com.example.feature_parts.component

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_data.domain.entity.*
import com.example.core_data.domain.usecase.GetComponentUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ComponentViewModel @Inject constructor(
    private val getComponentUseCase: GetComponentUseCase
) : ViewModel() {

    val componentsLiveData: MutableLiveData<List<Component>> = MutableLiveData<List<Component>>()
    val currentComponentLiveData: MutableLiveData<Component> = MutableLiveData<Component>()
    val currentItemsLiveData: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    var ratio: Float = 1f
    private var components: List<Component> = emptyList()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestComponent(url: String, baseUrl: String, innerUrl: String, type: ManufacturerType) {
        disposable.add(
            getComponentUseCase.getComponent(url, baseUrl, innerUrl, type)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        componentsLiveData.postValue(value)
                        currentComponentLiveData.postValue(value[0])
                        currentItemsLiveData.postValue(value[0].items)
                        components = value
                    },
                    { error -> error.printStackTrace() }
                )
        )
    }

    fun onComponentSelected(index: Int) {
        currentComponentLiveData.postValue(components[index])
        currentItemsLiveData.postValue(components[index].items)
    }

    fun convertCoordinates(
        resource: Bitmap,
        items: List<Item>,
        imageSize: ComponentImageSize
    ): List<Coordinates> {
        return items.map { item ->
            val resourceRatio: Float = resource.width.toFloat() / resource.height.toFloat()
            val imageSizeRatio: Float = imageSize.width / imageSize.height
            ratio = if (imageSize.width == 1f && imageSize.height == 1f) {
                1f
            } else if (imageSize.height != 1f && imageSize.width == 1f) {
                resource.height.toFloat() / imageSize.height
            }
            else if (
                resource.width.toFloat() != imageSize.width &&
                resource.height.toFloat() != imageSize.height &&
                resourceRatio / imageSizeRatio == 1f || imageSize.width / imageSize.height == 1f
            ) {
                resource.width.toFloat() / imageSize.width
            } else {
                resourceRatio / imageSizeRatio
            }

            Coordinates(
                x1 = item.coordinates.x1 * ratio,
                y1 = item.coordinates.y1 * ratio,
                x2 = item.coordinates.x2 * ratio,
                y2 = item.coordinates.y2 * ratio
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}