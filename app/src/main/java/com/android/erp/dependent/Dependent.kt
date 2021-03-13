package com.android.erp.dependent

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.erp.R
import com.android.erp.database.DbHelper
import com.android.erp.database.model.EmpModelClass
import com.android.erp.mainActivity.MainActivity

class Dependent : AppCompatActivity() {

    var handler: Handler? = null
    var db: DbHelper? = null
    var editName: EditText? = null
    var editDob: EditText? = null
    var editRel: EditText? = null
    private lateinit var mobile: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DbHelper(this)
        setContentView(R.layout.dialog_update_dependant)
        mobile = intent.getStringExtra("mobileNumber")
        editName = findViewById(R.id.etUpdateName)
        editDob = findViewById(R.id.tvUpdateDOB)
        editRel = findViewById(R.id.etUpdateRelation)
        DisplayDetails(mobile)
    }

    fun update(view: View) {
        val name = editName?.text.toString()
        val age = editDob?.text.toString()
        val rel = editRel?.text.toString()
        if (!name.isEmpty() && !mobile.isEmpty() && !age.isEmpty() && !rel.isEmpty()) {
            var del = db?.deleteDepData(mobile)
            var status = db?.depEmployee(EmpModelClass(name, mobile, "", age, "", "", rel))
            if (status != null) {
                if (status > -1) {
                    Toast.makeText(applicationContext, "Record Updated", Toast.LENGTH_LONG).show()
                    handler = Handler()
                    handler!!.postDelayed({
                        startActivity(Intent(this, MainActivity::class.java))
                    }, 1000)
                } else {

                    Toast.makeText(
                        applicationContext,
                        "Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun exit(view: View) {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun DisplayDetails(mobile: String) {
        val cursor = db?.fetchDependedDetails(mobile)
        if (cursor != null && cursor.moveToFirst()) do {
            val name = cursor.getString(cursor.getColumnIndex("Dep_Name"))
            val dob = cursor.getString(cursor.getColumnIndex("Dep_Dob"))
            val rel = cursor.getString(cursor.getColumnIndex("Dep_Relation"))
            editName?.setText(name)
            editDob?.setText(dob)
            editRel?.setText(rel)
        } while (cursor.moveToNext())

    }
}