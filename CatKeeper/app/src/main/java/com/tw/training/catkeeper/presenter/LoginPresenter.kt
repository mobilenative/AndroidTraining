package com.tw.training.catkeeper.presenter

import android.text.TextUtils
import android.util.Log

/**
 * Created by pchen on 17/10/2017.
 */
class LoginPresenter(view: LoginContract.View): LoginContract.Presenter {
    private val TAG = "LoginPresenter"
    private val loginView: LoginContract.View = view

    override fun startLogin(userName: String, password: String) {
        Log.d(TAG, "userName: $userName, " +
                "password: $password")

        if (isValidInfo(userName, password)) {
            loginView.loginSuccess()
        }
    }

    private fun isValidInfo(userName: String, password: String): Boolean {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Log.w(TAG, "userName or password are not allow empty")
            return false
        }
        return true
    }


}