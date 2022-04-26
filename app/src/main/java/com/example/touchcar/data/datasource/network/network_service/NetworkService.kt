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
        return Single.fromCallable {
            val document = getDocument(BuildConfig.BASE_URL)
            commonParser.getManufacturers(document)
        }

    }

    fun getModels(url: String): Single<List<Model>> {
        return Single.fromCallable {
            val document = getDocument(url)
            commonParser.getModels(document)
        }

    }

    fun getBodyList(url: String): Single<List<Body>> {
        return Single.fromCallable {
            val document = getDocument(url)
            commonParser.getBodyList(document)
        }

    }

    fun getEquipment(url:String, manufacturerType: ManufacturerType): Single<List<Equipment>> {
        return Single.fromCallable {
            val document = getDocument(url)
            commonParser.getEquipment(document, manufacturerType)
        }

    }

    private fun getDocument(url: String): Document {
        return Jsoup.connect(url).timeout(TIMEOUT).get()
    }

    companion object {
        private const val TIMEOUT = 10 * 10000
    }
}