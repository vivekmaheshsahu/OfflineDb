package com.android.erp.Otp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.erp.R
import com.android.erp.addEmployee.AddEmployee

class Otp : AppCompatActivity() {
    var handler: Handler? = null
    var editnumber: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        editnumber = findViewById(R.id.edit_mobile_number)
    }

    fun addEmp(view: View) {

        val value = editnumber?.text.toString()
        if (value == "") {
            Toast.makeText(applicationContext, "Fields cannot be blank", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, "OTP Successfull Sent!!", Toast.LENGTH_LONG).show()
            handler = Handler()
            handler!!.postDelayed({
                var intent = Intent(this, AddEmployee::class.java)
                startActivity(intent)
            }, 2000)

        }
    }
}