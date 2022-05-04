package com.example.touchcar.data.datasource.network.network_service.parsers.car

import com.example.touchcar.domain.entity.Car
import org.jsoup.nodes.Document

interface CarParser {

    fun parse(document: Document): Car
}