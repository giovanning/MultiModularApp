package com.projetos.filmei.login.presentation.error

import com.projetos.filmei.login.R

sealed class LoginError : Error() {
    abstract fun getErrorMessage(): Int

    // No Entry - default state
    data object NoEntry : LoginError() {
        override fun getErrorMessage(): Int = R.string.no_error
    }

    // No Error
    data object NoError : LoginError() {
        override fun getErrorMessage(): Int = R.string.no_error
    }

    // Incorrect Username
    data object IncorrectUserName : LoginError() {
        override fun getErrorMessage(): Int = R.string.username_error
    }

    // Incorrect Password
    data object IncorrectPassword : LoginError() {
        override fun getErrorMessage(): Int = R.string.password_error
    }

    // Incorrect Username length
    data object IncorrectUsernameLength : LoginError() {
        override fun getErrorMessage(): Int = R.string.username_length_error
    }

    // Incorrect Password length
    data object IncorrectPasswordLength : LoginError() {
        override fun getErrorMessage(): Int = R.string.password_length_error
    }
}