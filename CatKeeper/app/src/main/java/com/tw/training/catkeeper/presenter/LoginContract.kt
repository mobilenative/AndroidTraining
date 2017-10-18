package com.tw.training.catkeeper.presenter

/**
 * Created by pchen on 17/10/2017.
 */

interface LoginContract {
    interface View {
        fun loginSuccess()
    }
    interface Presenter {
        fun startLogin(userName: String, password: String)
    }
}