package com.exam.janus.api

import com.exam.janus.service.EmployeeDetailResponse
import com.exam.janus.service.EmployeesResponse
import com.exam.janus.service.ReportsResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //http://dummy.restapiexample.com/api/v1/employees
    @GET
    suspend fun getEmployees(@Url url: String = "api/v1/employees"): Response<ReportsResponse>

    @GET
    suspend fun getEmployeeDetail(@Url url: String = "api/v1/employee"):
            Response<EmployeeDetailResponse>

}