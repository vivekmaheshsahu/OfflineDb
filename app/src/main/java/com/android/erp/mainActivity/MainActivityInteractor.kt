package com.android.erp.mainActivity

import android.content.Context
import android.database.Cursor
import com.android.erp.database.DbHelper

class MainActivityInteractor() : IMainActivityInteractor {

    private var mContext: Context? = null
    private var dbHelper: DbHelper? = null

    constructor(mContext: Context) : this() {
        this.mContext = mContext
        dbHelper = DbHelper(mContext)
    }

    override fun fetchAllParticipants(): Cursor? {
        return dbHelper?.fetchAll()
    }
}