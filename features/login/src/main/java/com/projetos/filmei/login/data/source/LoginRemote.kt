package com.projetos.filmei.login.data.source

import com.projetos.filmei.domain.model.User
import com.projetos.filmei.domain.result.OutCome

interface LoginRemote {
    suspend fun login(userName: String, password: String): OutCome<User>
}
