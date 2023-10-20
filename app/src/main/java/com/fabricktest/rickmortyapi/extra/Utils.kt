package com.fabricktest.rickmortyapi.extra

import java.net.URL

fun String?.default(): String {
    return this ?: ""
}

fun getQueryParams(path: String): HashMap<String, String> {
    return try {
        val url = URL(path)
        val query = url.query

        val queryParams = HashMap<String, String>()

        val pairs = query.split("&".toRegex()).toTypedArray()

        for (pair in pairs) {
            val keyValue = pair.split("=".toRegex()).toTypedArray()
            if (keyValue.size == 2) {
                val key = keyValue[0]
                val value = keyValue[1]
                queryParams[key] = value
            }
        }
        queryParams
    } catch (e: Exception) {
        e.printStackTrace()
        hashMapOf()
    }
}