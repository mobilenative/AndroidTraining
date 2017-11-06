package com.tw.training.catkeeper.network


import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by pchen on 06/11/2017.
 */
class HttpUtils {
    fun doGet(urlString: String): String? {
        val url = URL(urlString)
        val response: String?
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            urlConnection.requestMethod = "GET"
            response = urlConnection.inputStream.bufferedReader().readText()
        } finally {
            urlConnection.disconnect()
        }

        return response
    }
}