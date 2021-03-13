package com.android.erp.mainActivity

import android.content.Context
import com.android.erp.database.model.EmpModelClass

interface IMainActivityView {

    fun getContext(): Context

    fun setAdapter(mEmpList: List<EmpModelClass>)

}