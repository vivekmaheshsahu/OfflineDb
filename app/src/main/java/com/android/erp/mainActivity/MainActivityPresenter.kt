package com.android.erp.mainActivity

import com.android.erp.database.model.EmpModelClass
import java.util.*

class MainActivityPresenter : IMainActivityPresenter<IMainActivityView> {

    var view: IMainActivityView? = null
    var interactor: IMainActivityInteractor? = null

    override fun getAllParticipants() {
        val womenList: MutableList<EmpModelClass> = ArrayList()
        val cursor = interactor?.fetchAllParticipants()
        if (cursor != null && cursor.moveToFirst()) do {
            womenList.add(
                EmpModelClass(
                    cursor.getString(cursor.getColumnIndex("Emp_Username")),
                    cursor.getString(cursor.getColumnIndex("EmpMobileNumber")),
                    cursor.getString(cursor.getColumnIndex("EmpEmail")),
                    cursor.getString(cursor.getColumnIndex("EmpMobileAge")),
                    cursor.getString(cursor.getColumnIndex("EmpDesignation")),
                    "",
                    ""
                )
            )
        } while (cursor.moveToNext())
        view?.setAdapter(womenList)
    }

    override fun attachView(view: IMainActivityView) {
        this.view = view
        interactor = MainActivityInteractor(view.getContext())
    }

}