package com.exam.janus.service

import com.exam.janus.api.Mapper

class EmployeeMapper : Mapper<EmployeeDetailResponse, EmployeeDetailResponse> {
    override suspend fun map(input: EmployeeDetailResponse): EmployeeDetailResponse {
        return EmployeeDetailResponse(
                status = input.status,
                message = input.message,
                data = input.data
        )
    }

}