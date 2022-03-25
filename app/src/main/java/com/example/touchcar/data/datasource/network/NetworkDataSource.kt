package com.example.touchcar.data.datasource.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.touchcar.data.datasource.network.entity.NetworkManufacturerModel
import io.reactivex.Single
import javax.inject.Inject

class NetworkDataSource @Inject constructor(var networkService: NetworkService) {

    @RequiresApi(Build.VERSION_CODES.N)
    fun getManufacturers(): Single<MutableList<NetworkManufacturerModel>> {
        return networkService.getManufacturers()
    }
}