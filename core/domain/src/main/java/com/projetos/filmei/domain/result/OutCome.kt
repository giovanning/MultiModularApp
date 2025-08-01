package com.projetos.filmei.domain.result

import com.projetos.filmei.domain.model.ErrorMessage
import com.projetos.filmei.domain.usecase.UseCase

sealed class OutCome<T> {

    abstract fun isSuccess(): Boolean
    open fun errorMessage(): ErrorMessage? = null
    abstract suspend fun accept(useCase: UseCase<T>)

    data class Success<T>(val data: T) : OutCome<T>() {
        override fun isSuccess(): Boolean = true

        override suspend fun accept(useCase: UseCase<T>) {
            useCase.onSuccess(this)
        }
    }

    class Error<T>(private val errorMessage: ErrorMessage) : OutCome<T>() {
        override fun isSuccess(): Boolean = false

        override fun errorMessage(): ErrorMessage = errorMessage

        override suspend fun accept(useCase: UseCase<T>) {
            useCase.onError(errorMessage)
        }
    }

    class Empty<T> : OutCome<T>() {
        override fun isSuccess(): Boolean = true

        override suspend fun accept(useCase: UseCase<T>) {
            useCase.onEmpty()
        }
    }

    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> error(errorMessage: ErrorMessage) = Error<T>(errorMessage)
        fun <T> empty() = Empty<T>()
    }
}
