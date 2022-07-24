package com.example.core_common.utils

fun String?.indexesOf(pat: String): List<Pair<Int, Int>> {
    return pat.toRegex(RegexOption.IGNORE_CASE)
        .findAll(this ?: "")
        .map { it.range.first }
        .toList()
        .chunked(2)
        .map {  Pair(it[0] + 1, it[1]) }
}
