package com.tw.training.catkeeper.presenter

import com.tw.training.catkeeper.model.GetNearbyCatResponse

interface NearbyCatContract {

    interface View : BaseView<Presenter> {
        fun onGetDataSucceed(data: List<GetNearbyCatResponse.MomentsBean>?)
        fun onGetDataFail(msg: String)
    }

    interface Presenter : BasePresenter
}