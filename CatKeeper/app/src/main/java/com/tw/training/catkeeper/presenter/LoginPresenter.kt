package com.tw.training.catkeeper.presenter

import android.text.TextUtils
import android.util.Log

/**
 * Created by pchen on 19/10/2017.
 */
class LoginPresenter(private val loginView: LoginContract.View): LoginContract.Presenter {

    private val TAG = "LoginPresenter"

    override fun startLogin(userName: String, password: String) {
        Log.d(TAG, "UserName: $userName, Password: $password")
        if(validateAccount(userName, password)) {
            //TODO call login api
            loginView.loginSuccess()
        }
    }

    private fun validateAccount(userName: String, password: String): Boolean {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Log.w(TAG, "your username or password is empty")
            return false
        }
        return true
    }

}