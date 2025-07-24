package com.projetos.filmei.data.mapper

import com.projetos.filmei.data.response.ErrorResponse
import com.projetos.filmei.domain.model.ErrorMessage

fun ErrorResponse.toDomain(code: Int): ErrorMessage {
    return ErrorMessage(
        code = code,
        message = errorMessage.orEmpty(),
        errorFieldList = errorFieldList ?: emptyList(),
    )
}
