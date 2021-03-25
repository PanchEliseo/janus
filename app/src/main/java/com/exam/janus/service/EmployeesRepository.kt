package com.exam.janus.service

import android.util.Log
import com.exam.janus.api.BaseRepository
import com.exam.janus.api.Resource
import retrofit2.HttpException

class EmployeesRepository: BaseRepository() {
    suspend fun getEmployees(): Resource<ReportsResponse> {
        return try {
            val response = service.getEmployees()
            if (response.isSuccessful){
                val mapper = response.body()?.let { ReportMapper().map(it) }
                Resource.success(mapper)
            } else
                throw HttpException(response)
        } catch (e: Exception) {
            e.printStackTrace()
            responseHandler.handleException(e)
        }
    }

    suspend fun getEmployeeDetail(employeeId: Int) : Resource<EmployeeDetailResponse> {
        return try {
            val response = service.getEmployeeDetail(url = "api/v1/employee/$employeeId")
            Log.i("URL", "--- ${response.raw().request.url}")
            if (response.isSuccessful){
                val mapper = response.body()?.let { EmployeeMapper().map(it) }
                Resource.success(mapper)
            } else
                throw HttpException(response)
        } catch (e: Exception) {
            e.printStackTrace()
            responseHandler.handleException(e)
        }
    }
}