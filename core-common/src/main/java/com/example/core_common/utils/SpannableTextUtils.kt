package com.example.core_common.utils

fun String.indexesOf(openPattern: String, closePattern: String): List<Pair<Int, Int>> {
    val openIndexes = openPattern.toRegex(RegexOption.IGNORE_CASE)
        .findAll(this)
        .map { it.range.first + 1 }
        .toList()
    val closeIndexes = closePattern.toRegex(RegexOption.IGNORE_CASE)
        .findAll(this)
        .map { it.range.first }
        .toList()

    return openIndexes.zip(closeIndexes)
}
