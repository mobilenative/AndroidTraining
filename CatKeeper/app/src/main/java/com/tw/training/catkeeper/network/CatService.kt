package com.tw.training.catkeeper.network

import com.tw.training.catkeeper.model.GetNearbyCatResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import rx.Observable

/**
 * Created by yhjiang on 13/11/2017.
 */
interface CatService {

    @GET("catnip/moment/")
    fun getNearbyCat(): Observable<GetNearbyCatResponse>


    companion object {
        fun getCatService(): CatService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://10.0.2.2:8080")
                    .build()
            return retrofit.create(CatService::class.java)

        }
    }
}