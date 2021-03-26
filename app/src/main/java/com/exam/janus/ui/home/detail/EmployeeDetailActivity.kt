package com.exam.janus.ui.home.detail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.exam.janus.EmployeesViewModel
import com.exam.janus.R
import com.exam.janus.api.Status
import com.exam.janus.service.EmployeesResponse
import com.exam.janus.ui.home.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_login.*

class EmployeeDetailActivity: AppCompatActivity() {

    private lateinit var employeeViewModel: EmployeesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        employeeViewModel =
                ViewModelProvider(this).get(EmployeesViewModel::class.java)
        val employeeId = intent.getIntExtra("employeeId", 0)
        subscribeLiveData()
        employeeViewModel.getEmployeeDetail(employeeId)
    }

    private fun setView(employee: EmployeesResponse){
        tvEmployeeIdDetail.text = employee.id.toString()
        tvEmployeeNameDetail.text = employee.employee_name
        val salary = "$"+employee.employee_salary
        if (employee.employee_salary > 1000)
            tvSalaryDetail.setTextColor(Color.parseColor("#00FF00"))
        else
            tvSalaryDetail.setTextColor(Color.parseColor("#FF0000"))
        tvSalaryDetail.text = salary
        tvAgeDetail.text = employee.employee_age.toString()
    }

    private fun subscribeLiveData(){
        employeeViewModel.employeeLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.i("ON", "LOGIN")
                    progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    //Log.i("ON", "SUCCESS ${it.data!!.data}")
                    setView(it.data!!.data)
                    progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    Log.i("ON", "ERROR")
                    progressBar.visibility = View.GONE
                    Snackbar.make(container, "Error en el servicio", Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

}