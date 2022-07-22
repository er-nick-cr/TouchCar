package com.example.core_data.data.network_service.parsers

import org.jsoup.nodes.Document
import javax.inject.Inject

class BaseUrlParser @Inject constructor() {

    fun parse(document: Document): String {
        return document.location().replaceAfter("epcdata.ru/", "")
    }
}