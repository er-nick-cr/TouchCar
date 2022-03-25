package com.example.touchcar.data.datasource.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.touchcar.data.datasource.network.entity.NetworkManufacturerModel
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class NetworkService @Inject constructor() {

    @RequiresApi(Build.VERSION_CODES.N)
    fun getManufacturers(): Single<MutableList<NetworkManufacturerModel>> {
        return Single.fromCallable{
            val document: Document = Jsoup.connect("https://www.epcdata.ru").get()
            val containers: Elements = document.select("tbody:first-of-type")
            val networkManufacturerModels: MutableList<NetworkManufacturerModel> = mutableListOf();

            for (container: Element in containers) {
                val manufacturerName: String = container.select("h1:first-of-type a").text()
                val manufacturerMarket: String = container.select("p:first-of-type").text()
                val manufacturerSide: String = container.select("h4:first-of-type").text()

                networkManufacturerModels.add(NetworkManufacturerModel(manufacturerName, manufacturerMarket + manufacturerSide))
            }
            networkManufacturerModels.removeIf { it.name == "" }
            networkManufacturerModels.removeAt(0)
            networkManufacturerModels
        }

    }


}