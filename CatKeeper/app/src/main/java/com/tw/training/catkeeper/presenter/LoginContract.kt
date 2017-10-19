package com.tw.training.catkeeper.presenter

/**
 * Created by pchen on 19/10/2017.
 */
interface LoginContract {
    interface Presenter {
        fun startLogin(userName: String, password: String)
    }
    interface View {
        fun loginSuccess()
    }
}