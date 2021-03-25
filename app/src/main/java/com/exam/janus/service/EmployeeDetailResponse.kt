package com.exam.janus.service

import com.google.gson.annotations.SerializedName

data class EmployeeDetailResponse(
        @SerializedName("status")
        val status: String?,
        @SerializedName("data")
        val data: EmployeesResponse,
        @SerializedName("message")
        val message: String?
)