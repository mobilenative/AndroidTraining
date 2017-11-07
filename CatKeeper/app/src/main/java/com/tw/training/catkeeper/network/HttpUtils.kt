package com.tw.training.catkeeper.network


import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

    fun doDownloadImage(urlString: String): Bitmap? {
        val url = URL(urlString)
        val bitmap: Bitmap?
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            urlConnection.doInput = true
            urlConnection.connect()
            bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream())
        } finally {
            urlConnection.disconnect()
        }
        return bitmap
    }
}