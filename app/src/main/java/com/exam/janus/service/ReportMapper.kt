package com.exam.janus.service

import com.exam.janus.api.Mapper

class ReportMapper: Mapper<ReportsResponse, ReportsResponse> {
    override suspend fun map(input: ReportsResponse): ReportsResponse {
        return ReportsResponse(
                status = input.status,
                data = input.data,
                message = input.message
        )
    }
}