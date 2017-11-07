package com.tw.training.catkeeper.presenter

import android.os.AsyncTask
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.tw.training.catkeeper.domain.CatsNearby
import com.tw.training.catkeeper.network.HttpUtils
import org.json.JSONObject

/**
 * Created by pchen on 27/10/2017.
 */
class CatsNearbyPresenter(private val mCatsNearbyView: CatsNearbyContract.View):
        CatsNearbyContract.Presenter {

    private var CATS_NEARBY_URL = "http://10.0.2.2:8080/catnip/moment/"
    private var DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private var mCatsNearbyTask: CatsNearbyAsyncTask? = null

    override fun start() {
        loadingCatsNearbyList()
    }

    private fun loadingCatsNearbyList() {
         //Fetch Cats List
//        val catsNearbyList = ArrayList<CatsNearby>()
//        (1..100).forEach {
//            val catsNearby = CatsNearby("Kitty$it", "6 minutes ago",
//                    listOf(R.drawable.cat_item_1, R.drawable.cat_item_2, R.drawable.cat_item_3))
//            catsNearbyList.add(catsNearby)
//        }
//        mCatsNearbyView.showNearbyCats(catsNearbyList)
        if(mCatsNearbyTask == null) {
            mCatsNearbyTask = CatsNearbyAsyncTask()
        }
        mCatsNearbyTask?.execute(CATS_NEARBY_URL)
    }

    inner class CatsNearbyAsyncTask: AsyncTask<String, Unit, List<CatsNearby>>() {
        override fun doInBackground(vararg params: String?): List<CatsNearby> {
            val response = HttpUtils().doGet(params[0]!!)
            val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()
            return gson.fromJson(JSONObject(response).getString("moments"),
                    object: TypeToken<List<CatsNearby>>(){}.type)

        }

        override fun onPostExecute(result: List<CatsNearby>?) {
            super.onPostExecute(result)
            Log.d("CatsOnline", "moment size is: " + result?.size)
            mCatsNearbyView.showNearbyCats(result)
        }

    }
}