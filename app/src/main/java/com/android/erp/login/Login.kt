package com.android.erp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.erp.Otp.Otp
import com.android.erp.R
import com.android.erp.database.DbHelper
import com.android.erp.mainActivity.MainActivity

class Login : AppCompatActivity() {

    var editnumber: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editnumber = findViewById(R.id.edit_mobile_number)
    }

    fun signup(view: View) {
        val intent = Intent(this, Otp::class.java)
        startActivity(intent)
    }

    fun login(view: View) {
        var dbHelper = DbHelper(this)
        var number = editnumber?.text.toString()
        if (number.equals("")) {
            Toast.makeText(applicationContext, "Invalid Number", Toast.LENGTH_LONG).show()
        } else {
            var result = dbHelper.checkUserAlreadyRegistered(number)
            if (result.equals("")) {
                Toast.makeText(applicationContext, "Invalid Number", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Login Successfull!!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


}