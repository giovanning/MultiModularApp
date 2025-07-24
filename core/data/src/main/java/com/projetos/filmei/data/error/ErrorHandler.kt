package com.projetos.filmei.data.error

import com.google.gson.Gson
import com.projetos.filmei.data.response.ErrorResponse

fun getDefaultErrorResponse() = ErrorResponse("", "", emptyList())

fun getErrorResponse(gson: Gson, errorBody: String?): ErrorResponse =
    try {
        gson.fromJson(errorBody, ErrorResponse::class.java)
    } catch (e: Exception) {
        getDefaultErrorResponse()
    }
