package com.projetos.filmei.data.result

import com.projetos.filmei.data.model.ErrorMessage

interface UseCase<R> {
    suspend fun onSuccess(success: OutCome.Success<R>)

    suspend fun onEmpty()

    suspend fun onError(error: ErrorMessage)
}
