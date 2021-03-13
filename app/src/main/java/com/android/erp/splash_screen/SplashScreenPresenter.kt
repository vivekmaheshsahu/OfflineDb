package com.android.erp.splash_screen

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.erp.database.DatabaseContract
import com.android.erp.database.DatabaseManager
import com.android.erp.database.DbHelper
import com.android.erp.utility.Utility
import java.util.*

class SplashScreenPresenter : ISplashScreenPresenter<ISplashScreenView> {

    var iSplashScreenview: ISplashScreenView? = null
    var uti = Utility()
    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.READ_PHONE_STATE
    )

    override fun attachView(view: ISplashScreenView?) {
        this.iSplashScreenview = view
        if (checkPermissions()) {
            initializeDBHelper()
            iSplashScreenview?.login()
        }
    }

    override fun initializeDBHelper() {
        var dbHelper = DbHelper(iSplashScreenview!!.context.applicationContext)
        DatabaseManager.initializeInstance(dbHelper)
        DatabaseManager.getInstance().openDatabase()
    }

    override fun checkPermissions(): Boolean {
        val listPermissionsNeeded = ArrayList<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    iSplashScreenview!!.context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                listPermissionsNeeded.add(permission)
            }
        }
        return if (!listPermissionsNeeded.isEmpty()) {
            getPermissions(listPermissionsNeeded)
            false
        } else {
            true
        }
    }


    override fun getPermissions(listPermissionsNeeded: List<String>) {
        ActivityCompat.requestPermissions(
            (iSplashScreenview!!.context as Activity),
            listPermissionsNeeded.toTypedArray(), 1
        )
    }

    override fun checkIfUserAlreadyLoggedIn(): Boolean {
        var db = DbHelper(iSplashScreenview?.context)
        if (db.checkDataBase()) {
            var cursor = Utility.getDatabase().rawQuery(
                "SELECT * FROM "
                        + DatabaseContract.TABLE_NAME_EMP, null
            )
            return cursor.moveToFirst()
        }
        return false
    }
}