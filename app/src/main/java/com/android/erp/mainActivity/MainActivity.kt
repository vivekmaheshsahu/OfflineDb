package com.android.erp.mainActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.erp.R
import com.android.erp.addEmployee.AddEmployee
import com.android.erp.database.DbHelper
import com.android.erp.database.model.EmpModelClass
import com.android.erp.dependent.Dependent

class MainActivity : AppCompatActivity(), IMainActivityView {

    var emptyLayout: ConstraintLayout? = null
    var adapterDetails: AdapaterDetails? = null
    var presenter: IMainActivityPresenter<*>? = null
    var mRecyclerView: RecyclerView? = null
    var db: DbHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DbHelper(this)
        mRecyclerView = findViewById(R.id.rvItemsList)
        emptyLayout = findViewById(R.id.empty_midline_list)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        var itemDecoration: RecyclerView.ItemDecoration =
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        mRecyclerView?.addItemDecoration(itemDecoration)
        mRecyclerView?.layoutManager = layoutManager
        presenter = MainActivityPresenter()
        (presenter as MainActivityPresenter).attachView(this)
    }

    override fun setAdapter(mEmpList: List<EmpModelClass>) {
        if (mEmpList == null || mEmpList.size < 1) {
            emptyLayout?.visibility = View.VISIBLE
            return
        }
        if (adapterDetails == null) {
            adapterDetails = AdapaterDetails(
                getContext(),
                mEmpList,
                object : AdapaterDetails.OnItemClickListener {
                    override fun onItemClick(uniqueId: String, mobileNumber: String, flag: String) {
                        if (flag.equals("1")) {
                            UpdateDependentDetails(mobileNumber, uniqueId)
                        } else {
                            deleteRecordAlertDialog(mobileNumber, uniqueId)
                        }
                    }
                })
            mRecyclerView?.adapter = adapterDetails
        } else {
            adapterDetails?.swapDataList(mEmpList)
            adapterDetails?.notifyDataSetChanged()
        }
    }

    override fun getContext(): Context {
        return this
    }

    override fun onResume() {
        super.onResume()
        LoadData()
    }

    fun LoadData() {
        presenter?.getAllParticipants()
    }

    fun fab(view: View) {
        var intent = Intent(this@MainActivity, AddEmployee::class.java)
        startActivity(intent)
    }

    /**
     * Method is used to show the delete alert dialog.
     */
    fun deleteRecordAlertDialog(mob: String, name: String) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Delete Record")
        //set message for alert dialog
        builder.setMessage("Are you sure you wants to delete ${name}.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->

            val status = db?.deleteData(mob)
            if (status == 1) {
                Toast.makeText(
                    applicationContext,
                    "Record deleted successfully.",
                    Toast.LENGTH_SHORT
                ).show()
                LoadData()
            }

            dialogInterface.dismiss() // Dialog will be dismissed
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI
    }

    fun UpdateDependentDetails(mob: String, name: String) {

        val intent = Intent(this, Dependent::class.java)
        intent.putExtra("mobileNumber", mob)
        startActivity(intent)
    }

}