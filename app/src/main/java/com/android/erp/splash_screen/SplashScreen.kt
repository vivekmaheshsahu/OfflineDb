package com.android.erp.splash_screen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.android.erp.R
import com.android.erp.login.Login
import com.android.erp.mainActivity.MainActivity


class SplashScreen : AppCompatActivity(), ISplashScreenView {
    var handler: Handler? = null
    var splashPresenter: SplashScreenPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_scree)
        handler = Handler()
        handler!!.postDelayed({
            checkUserLogin()
        }, 1000)
    }

    override fun getContext(): Context {
        return this
    }

    override fun checkUserLogin() {
        splashPresenter = SplashScreenPresenter()
        splashPresenter?.attachView(this)
    }

    override fun login() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    override fun mainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {
            if (splashPresenter?.checkPermissions() == true) {
                if (splashPresenter?.checkIfUserAlreadyLoggedIn() == true) {
                    mainActivity()
                } else {
                    splashPresenter?.attachView(this)
                }
            } else {
                checkUserLogin()
            }

        }
    }

}