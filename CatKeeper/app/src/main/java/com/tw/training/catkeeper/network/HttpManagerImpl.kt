package com.tw.training.catkeeper.network

import com.tw.training.catkeeper.model.GetNearbyCatResponse

import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HttpManagerImpl : HttpManager {

    override fun getCat(callback: Subscriber<GetNearbyCatResponse>) {
        CatService.getCatService().getNearbyCat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback)
    }
}