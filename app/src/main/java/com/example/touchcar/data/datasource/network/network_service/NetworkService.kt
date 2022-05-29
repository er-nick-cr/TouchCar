package com.example.touchcar.data.datasource.network.network_service

import com.example.touchcar.BuildConfig
import com.example.touchcar.data.datasource.network.network_service.parsers.*
import com.example.touchcar.domain.entity.*
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class NetworkService @Inject constructor(
    private val commonParser: CommonParser
) {

    fun getManufacturers(): Single<List<Manufacturer>> {
        return requestDocument(BuildConfig.BASE_URL)
            .map { document -> commonParser.getManufacturers(document) }
    }

    fun getModels(url: String): Single<List<Model>> {
        return requestDocument(url)
            .map { document -> commonParser.getModels(document) }
    }

    fun getBodyList(url: String): Single<List<Body>> {
        return requestDocument(url)
            .map { document -> commonParser.getBodyList(document) }
    }
    fun getEquipment(url:String, manufacturerType: ManufacturerType): Single<List<Equipment>> {
        return requestDocument(url)
            .map { document -> commonParser.getEquipment(document, manufacturerType) }
    }

    fun getCar(url: String, type: ManufacturerType): Single<Car> {
        return requestDocument(url).map { document -> commonParser.getCar(document, type) }
    }

    fun getPartsData(url: String, type: ManufacturerType): Single<PartsData> {
        return requestDocument(url).map { document -> commonParser.getPartsData(document, type) }
    }

    private fun requestDocument(url: String): Single<Document> {
        return Single.fromCallable {
            Jsoup.connect(url).timeout(TIMEOUT).get()
        }
    }

    companion object {
        private const val TIMEOUT = 10 * 10000
    }
}