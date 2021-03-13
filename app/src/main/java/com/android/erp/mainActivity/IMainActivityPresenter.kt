package com.android.erp.mainActivity

import com.android.erp.utility.IBasePresenter

interface IMainActivityPresenter<V> : IBasePresenter<V> {

    fun getAllParticipants()
}