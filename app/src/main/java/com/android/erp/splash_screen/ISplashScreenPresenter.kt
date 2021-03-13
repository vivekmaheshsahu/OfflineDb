package com.android.erp.splash_screen

import com.android.erp.utility.IBasePresenter

interface ISplashScreenPresenter<V> : IBasePresenter<V> {
    fun initializeDBHelper()
    fun checkPermissions(): Boolean
    fun getPermissions(listPermissionsNeeded: List<String>)
    fun checkIfUserAlreadyLoggedIn(): Boolean
}