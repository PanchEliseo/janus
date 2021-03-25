package com.exam.janus.api

interface Mapper<I, O> {
    suspend fun map(input: I): O
}