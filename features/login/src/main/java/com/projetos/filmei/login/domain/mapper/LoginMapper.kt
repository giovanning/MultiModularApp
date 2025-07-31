package com.projetos.filmei.login.domain.mapper

import com.projetos.filmei.domain.model.User
import com.projetos.filmei.login.data.response.UserResponse

interface LoginMapper {
    suspend fun toDomain(userResponse: UserResponse): User
}
