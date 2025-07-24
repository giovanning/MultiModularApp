package com.projetos.filmei.domain.usecase

import com.projetos.filmei.domain.model.ErrorMessage
import com.projetos.filmei.domain.result.OutCome

interface UseCase<R> {
    suspend fun onSuccess(success: OutCome.Success<R>)

    suspend fun onEmpty()

    suspend fun onError(error: ErrorMessage)
}
