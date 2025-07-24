package com.projetos.filmei.data.error

import com.google.gson.Gson
import com.projetos.filmei.data.model.ErrorMessage
import com.projetos.filmei.data.response.ErrorResponse

fun ErrorResponse.toDomain(code: Int): ErrorMessage {
    return ErrorMessage(
        code = code,
        message = errorMessage.orEmpty(),
        errorFieldList = errorFieldList ?: emptyList(),
    )
}

fun getDefaultErrorResponse() = ErrorResponse("", "", emptyList())

fun getErrorResponse(gson: Gson, errorBody: String?): ErrorResponse =
    try {
        gson.fromJson(errorBody, ErrorResponse::class.java)
    } catch (e: Exception) {
        getDefaultErrorResponse()
    }
