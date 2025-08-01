package com.projetos.filmei.domain.usecase

import com.projetos.filmei.domain.model.ErrorMessage
import com.projetos.filmei.domain.result.OutCome

abstract class AsyncUseCase<I, R> : UseCase<R> {
    private lateinit var success: suspend (R) -> Unit
    private lateinit var empty: suspend () -> Unit
    private lateinit var error: suspend (ErrorMessage) -> Unit

    suspend fun execute(
        input: I,
        success: suspend (R) -> Unit = {},
        empty: suspend () -> Unit = {},
        error: suspend (ErrorMessage) -> Unit = {},
    ) {
        this.success = success
        this.empty = empty
        this.error = error

        run(input).accept(this)
    }

    abstract suspend fun run(input: I): OutCome<R>

    override suspend fun onSuccess(success: OutCome.Success<R>) {
        success(success.data)
    }

    override suspend fun onEmpty() {
        empty()
    }

    override suspend fun onError(error: ErrorMessage) {
        error(error)
    }
}
