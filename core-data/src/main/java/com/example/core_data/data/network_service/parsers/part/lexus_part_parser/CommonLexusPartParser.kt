package com.example.core_data.data.network_service.parsers.part.lexus_part_parser

import com.example.core_data.data.network_service.parsers.part.PartParser
import com.example.core_data.domain.entity.Part
import org.jsoup.nodes.Document
import javax.inject.Inject

internal class CommonLexusPartParser @Inject constructor(
    private val lexusInternationalPartParser: LexusInternationalPartParser,
    private val lexusJapanPartParser: LexusJapanPartParser
) : PartParser {

    override fun parse(document: Document): List<Part> {
        val parser = getParser(document)
        return parser.parse(document)
    }

    private fun getParser(document: Document) : LexusPartParser {
        val url: String = document.location().toString()
        val isInternational: Boolean = url.contains("usa") || url.contains("europe") || url.contains("general")
        return if (isInternational) {
            lexusInternationalPartParser
        } else {
            lexusJapanPartParser
        }
    }
}