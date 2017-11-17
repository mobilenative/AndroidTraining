package com.tw.training.catkeeper.network

class HttpManagerFactory {
    companion object {
        private val sInstance: HttpManager = HttpManagerImpl()

        fun getHttpManager(): HttpManager {
            return sInstance
        }
    }
}