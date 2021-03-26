package com.exam.janus

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.exam.janus.api.Status
import com.exam.janus.service.ReportsResponse

class SplashActivity: AppCompatActivity() {

    private lateinit var viewModel: EmployeesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProvider(this).get(EmployeesViewModel::class.java)
        subscribeLiveData()
        viewModel.getEmployees()
    }

    private fun subscribeLiveData(){
        viewModel.employeesLiveData.observe(this, Observer {
            when(it.status){
                Status.LOADING -> {
                    Log.i("ON", "LOADING")
                }
                Status.SUCCESS -> {
                    val dataReport = it.data as ReportsResponse
                    val list = ArrayList(dataReport.data)
                    Log.i("SUCCESS", "--- ${App.instance.sharedPreferencesHelper.getLogin()}")
                    if (App.instance.sharedPreferencesHelper.getLogin() != 0) {
                        val intent = Intent(this, MainActivity::class.java).apply {
                            putParcelableArrayListExtra("report", list)
                            //putExtra("report", dataReport)
                        }
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, LoginActivity::class.java).apply {
                            putParcelableArrayListExtra("report", list)
                            //putExtra("report", dataReport)
                        }
                        startActivity(intent)
                    }
                    finish()
                }
                Status.ERROR -> {
                    Log.i("ON", "ERROR")
                    finish()
                }
            }
        })
    }

}