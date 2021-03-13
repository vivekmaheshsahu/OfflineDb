package com.android.erp.splash_screen

import com.android.erp.utility.IMvpView

interface ISplashScreenView : IMvpView {

    fun checkUserLogin()
    fun login()
    fun mainActivity()
}