package com.projetos.filmei.login.domain.usecase

import com.projetos.filmei.domain.model.User
import com.projetos.filmei.domain.result.OutCome
import com.projetos.filmei.domain.usecase.AsyncUseCase
import com.projetos.filmei.login.data.source.LoginRemote
import javax.inject.Inject

class LoginUseCase@Inject constructor(private val loginRemote: LoginRemote) :
    AsyncUseCase<LoginUseCase.Input, User>() {

    override suspend fun run(input: Input): OutCome<User> {
        return loginRemote.login(input.userName, input.password)
    }

    data class Input(val userName: String, val password: String)
}
