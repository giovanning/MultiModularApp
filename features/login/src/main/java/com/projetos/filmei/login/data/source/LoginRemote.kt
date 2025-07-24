package com.projetos.filmei.login.data.source

import com.projetos.filmei.data.result.OutCome
import com.projetos.filmei.login.data.request.LoginRequestBody
import com.projetos.filmei.login.domain.model.User

interface LoginRemote {
    suspend fun login(loginRequestBody: LoginRequestBody): OutCome<User>
}
