package com.tw.training.catkeeper.utils

import android.graphics.Bitmap
import android.os.AsyncTask
import android.widget.ImageView
import com.tw.training.catkeeper.network.HttpUtils

/**
 * Created by pchen on 07/11/2017.
 */
fun ImageView.loadImageUrl(urlString: String) {
    val imageView = this
    object : AsyncTask<String, Unit, Bitmap>() {
        override fun doInBackground(vararg params: String?): Bitmap? {
            return HttpUtils().doDownloadImage(params[0]!!)
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            imageView.setImageBitmap(result)
        }

    }.execute(urlString)
}

