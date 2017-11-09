package com.tw.training.catkeeper.presenter

import android.os.AsyncTask
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.tw.training.catkeeper.domain.CatsNearby
import com.tw.training.catkeeper.network.Constants.Companion.CATS_NEARBY_URL
import com.tw.training.catkeeper.network.HttpUtils
import org.json.JSONObject

/**
 * Created by pchen on 27/10/2017.
 */
class CatsNearbyPresenter(private val mCatsNearbyView: CatsNearbyContract.View):
        CatsNearbyContract.Presenter {
    companion object {
        private val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        class CatsNearbyAsyncTask(val view: CatsNearbyContract.View): AsyncTask<String, Unit, List<CatsNearby>>() {
            override fun doInBackground(vararg params: String?): List<CatsNearby> {
                val response = HttpUtils().doGet(params[0]!!)
                val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()
                return gson.fromJson(JSONObject(response).getString("moments"),
                        object: TypeToken<List<CatsNearby>>(){}.type)

            }
            override fun onPostExecute(result: List<CatsNearby>?) {
                super.onPostExecute(result)
                Log.d("CatsOnline", "moment size is: " + result?.size)
                view.showNearbyCats(result)
            }

        }
    }
    private var mCatsNearbyTask: CatsNearbyAsyncTask? = null

    override fun start() {
        loadingCatsNearbyList()
    }

    private fun loadingCatsNearbyList() {
        mCatsNearbyTask = CatsNearbyAsyncTask(mCatsNearbyView)
        mCatsNearbyTask?.execute(CATS_NEARBY_URL)
    }

    override fun stop() {
        mCatsNearbyTask?.cancel(true)
    }
}