package com.example.core_data.data.network_service.parsers.component

import com.example.core_data.domain.entity.Component
import com.example.core_data.domain.entity.ManufacturerType
import org.jsoup.nodes.Document
import javax.inject.Inject

internal class CommonComponentParser @Inject constructor(
    private val toyotaComponentParser: ToyotaComponentParser,
    private val nissanComponentParser: NissanComponentParser,
    private val mitsubishiComponentParser: MitsubishiComponentParser,
    private val mazdaComponentParser: MazdaComponentParser,
    private val hondaComponentParser: HondaComponentParser,
    private val lexusComponentParser: LexusComponentParser,
    private val subaruComponentParser: SubaruComponentParser,
    private val suzukiComponentParser: SuzukiComponentParser,
) {

    fun parse(document: Document, baseUrl: String, innerUrl: String, type: ManufacturerType): List<Component> {
        val parser = getCarParser(type)
        return parser.parse(document, baseUrl, innerUrl)
    }

    private fun getCarParser(type: ManufacturerType): ComponentParser {
        return when (type) {
            ManufacturerType.TOYOTA -> toyotaComponentParser
            ManufacturerType.NISSAN -> nissanComponentParser
            ManufacturerType.MITSUBISHI -> mitsubishiComponentParser
            ManufacturerType.MAZDA -> mazdaComponentParser
            ManufacturerType.HONDA -> hondaComponentParser
            ManufacturerType.LEXUS -> lexusComponentParser
            ManufacturerType.SUBARU -> subaruComponentParser
            ManufacturerType.SUZUKI -> suzukiComponentParser
            else -> nissanComponentParser
        }
    }
}