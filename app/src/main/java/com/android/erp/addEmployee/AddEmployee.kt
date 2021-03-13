package com.android.erp.addEmployee

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.erp.R
import com.android.erp.database.DbHelper
import com.android.erp.database.model.EmpModelClass
import com.android.erp.mainActivity.MainActivity

class AddEmployee : AppCompatActivity() {

    var editName: EditText? = null
    var editAge: EditText? = null
    var editMobile: EditText? = null
    var editEmail: EditText? = null
    var editDesignation: EditText? = null
    var editDepartment: EditText? = null
    var rbGender: RadioGroup? = null
    private lateinit var radioButton: RadioButton
    var RadioButton_value: String? = null
    var db: DbHelper? = null
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)
        db = DbHelper(this)
        editName = findViewById(R.id.etName)
        editAge = findViewById(R.id.etAge)
        editMobile = findViewById(R.id.etMobile)
        editEmail = findViewById(R.id.etEmailId)
        editDesignation = findViewById(R.id.etDes)
        editDepartment = findViewById(R.id.etDept)
        rbGender = findViewById(R.id.RGgender)
        rbGender?.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                RadioButton_value = radio.text as String?
            })
    }

    fun save(view: View) {
        val name = editName?.text.toString()
        val age = editAge?.text.toString()
        val mobile = editMobile?.text.toString()
        val email = editEmail?.text.toString()
        val designation = editDesignation?.text.toString()
        val dept = editDepartment?.text.toString()

        if (!name.isEmpty() && !mobile.isEmpty() && !email.isEmpty() && !age.isEmpty() && !RadioButton_value?.isEmpty()!! && !designation.isEmpty() && !dept.isEmpty()) {

            var status = db?.addEmployee(
                EmpModelClass(
                    name,
                    mobile,
                    email,
                    age,
                    RadioButton_value!!,
                    designation,
                    dept
                )
            )
            if (status != null) {
                if (status > -1) {
                    Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                    editName?.text?.clear()
                    editEmail?.text?.clear()
                    editMobile?.text?.clear()
                    editAge?.text?.clear()
                    editDesignation?.text?.clear()
                    editDepartment?.text?.clear()
                    handler = Handler()
                    handler!!.postDelayed({
                        startActivity(Intent(this, MainActivity::class.java))
                    }, 1000)
                } else {
                    handler = Handler()
                    handler!!.postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }, 1000)
                }
            }
        } else {
            Toast.makeText(applicationContext, "Fields cannot be blank", Toast.LENGTH_LONG).show()
        }
    }

}