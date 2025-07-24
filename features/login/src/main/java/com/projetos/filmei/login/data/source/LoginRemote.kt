package com.projetos.filmei.login.data.source

import com.projetos.filmei.domain.result.OutCome
import com.projetos.filmei.login.domain.model.User

interface LoginRemote {
    suspend fun login(userName: String, password: String): OutCome<User>
}
