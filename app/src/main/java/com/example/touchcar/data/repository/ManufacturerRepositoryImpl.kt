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
    private val networkDataSource: NetworkDataSource,
    private val manufacturerFromNetworkToDomainMapper: ManufacturerFromNetworkToDomainMapper
    ) : ManufacturerRepository {

    override fun getManufacturers(): Single<List<Manufacturer>> {
        return networkDataSource.getManufacturers().map { manufacturerFromNetworkToDomainMapper.map(it) }
    }
}