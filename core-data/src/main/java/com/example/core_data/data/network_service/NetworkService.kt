package com.example.core_data.data.network_service

import com.example.core_data.BuildConfig
import com.example.core_data.data.network_service.parsers.CommonParser
import com.example.core_data.domain.entity.*
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

internal class NetworkService @Inject constructor(
    private val commonParser: CommonParser,
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

    fun getComponent(url: String, baseUrl: String, innerUrl: String, type: ManufacturerType): Single<List<Component>> {
        return requestDocument(url).map { document -> commonParser.getComponent(document, baseUrl, innerUrl, type) }
    }

    fun getDetailedPart(url: String, type: ManufacturerType): Single<DetailedPart> {
        return requestDocument(url).map { document -> commonParser.getDetailedPart(document, type) }
    }

    fun getBaseUrl(url: String): Single<String> {
        return requestDocument(url).map { document -> commonParser.getBaseUrl(document) }
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