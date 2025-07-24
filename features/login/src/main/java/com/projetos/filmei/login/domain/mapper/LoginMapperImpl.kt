package com.projetos.filmei.login.domain.mapper

import com.projetos.filmei.login.data.response.UserResponse
import com.projetos.filmei.login.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginMapperImpl(private val defaultDispatcher: CoroutineDispatcher) : LoginMapper {
    override suspend fun toDomain(userResponse: UserResponse): User {
        return withContext(defaultDispatcher) {
            User(
                id = userResponse.id.orEmpty(),
                fullName = userResponse.fullName.orEmpty(),
                email = userResponse.email.orEmpty(),
                photo = userResponse.photo.orEmpty(),
            )
        }
    }
}
