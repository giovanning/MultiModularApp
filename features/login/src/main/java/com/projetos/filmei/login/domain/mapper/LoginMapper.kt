package com.projetos.filmei.login.domain.mapper

import com.projetos.filmei.login.data.response.UserResponse
import com.projetos.filmei.login.domain.model.User

interface LoginMapper {
    suspend fun toDomain(userResponse: UserResponse): User
}
