package com.example.core_common

interface BaseListItem {
    val id: String
}

class ListItem<T>(override val id: String, val item: T) : BaseListItem