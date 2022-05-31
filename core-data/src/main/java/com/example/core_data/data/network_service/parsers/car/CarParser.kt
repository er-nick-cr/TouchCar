package com.example.core_data.data.network_service.parsers.car

import com.example.core_data.domain.entity.Car
import org.jsoup.nodes.Document

internal interface CarParser {

    fun parse(document: Document): Car
}