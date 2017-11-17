package com.tw.training.catkeeper.presenter

import com.tw.training.catkeeper.model.GetNearbyCatResponse
import com.tw.training.catkeeper.network.HttpManagerFactory
import rx.Subscriber

class NearybyCatPresenter(val view: NearbyCatContract.View) : NearbyCatContract.Presenter {

    override fun onStart() {

        HttpManagerFactory.getHttpManager().getCat(object : Subscriber<GetNearbyCatResponse>() {
            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
                view.onGetDataFail(e.toString())
            }

            override fun onNext(response: GetNearbyCatResponse?) {
                view.onGetDataSucceed(response!!.moments)
            }

        })

    }

    override fun onStop() {
    }
}