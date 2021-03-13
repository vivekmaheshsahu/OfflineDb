package com.android.erp.mainActivity

import android.database.Cursor

interface IMainActivityInteractor {

    fun fetchAllParticipants(): Cursor?

}