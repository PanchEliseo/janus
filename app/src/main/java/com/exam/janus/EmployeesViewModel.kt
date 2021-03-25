package com.exam.janus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exam.janus.api.Resource
import com.exam.janus.service.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class EmployeesViewModel: ViewModel() {

    val employeesLiveData = MutableLiveData<Resource<ReportsResponse>>()
    val employeeLiveData = MutableLiveData<Resource<EmployeeDetailResponse>>()
    private val employeesRepository by lazy {
        EmployeesRepository()
    }

    fun getEmployees(){
        try {
            viewModelScope.launch {
                employeesLiveData.value = Resource.loading(null)
                val responseData = employeesRepository.getEmployees()
                withContext(Dispatchers.IO){
                    employeesLiveData.postValue(responseData)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getEmployeeDetail(employeeId: Int){
        try {
            viewModelScope.launch {
                employeeLiveData.value = Resource.loading(null)
                val responseData = employeesRepository.getEmployeeDetail(employeeId)
                withContext(Dispatchers.IO){
                    employeeLiveData.postValue(responseData)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}