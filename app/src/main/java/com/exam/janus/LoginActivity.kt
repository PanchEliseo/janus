package com.exam.janus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.exam.janus.service.EmployeesResponse
import com.exam.janus.service.ReportsResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var reports = arrayListOf<EmployeesResponse>()
    var incorrect = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getExtras()
        setListeners()
    }

    private fun setListeners(){
        btnLogin.setOnClickListener {
            if (etUser.text.isNotEmpty()){
                for (report in reports) {
                    if (etUser.text!!.toString().toInt() == report.id) {
                        incorrect = false
                        Log.i("LOGIN", "--- ${report.id}")
                        App.instance.sharedPreferencesHelper.setLogin(report.id)
                        val intent = Intent(this, MainActivity::class.java).apply {
                            putParcelableArrayListExtra("report", reports)
                        }
                        startActivity(intent)
                        finish()
                        break
                    } else
                        incorrect = true
                }
                if (incorrect)
                    Snackbar.make(container, "No estas registrado", Snackbar.LENGTH_LONG).show()
            } else
                Snackbar.make(container, "Usuario no encontrado", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun getExtras(){
        reports = intent.getParcelableArrayListExtra<EmployeesResponse>("report")!!
    }
}