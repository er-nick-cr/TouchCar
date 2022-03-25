package com.example.touchcar.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.touchcar.data.datasource.network.NetworkDataSource
import com.example.touchcar.data.mapper.ManufacturerFromNetworkToDomainMapper
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.repository.ManufacturerRepository
import io.reactivex.Single
import javax.inject.Inject

class ManufacturerRepositoryImpl @Inject constructor(
    val networkDataSource: NetworkDataSource,
    val manufacturerFromNetworkToDomainMapper: ManufacturerFromNetworkToDomainMapper
    ) : ManufacturerRepository {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getManufacturers(): Single<List<Manufacturer>> {
        return networkDataSource.getManufacturers().map { manufacturerFromNetworkToDomainMapper.map(it) }
    }
}