package com.tw.training.catkeeper.network

import com.tw.training.catkeeper.model.GetNearbyCatResponse
import rx.Subscriber

interface HttpManager {
    fun getCat(callback: Subscriber<GetNearbyCatResponse>)
}