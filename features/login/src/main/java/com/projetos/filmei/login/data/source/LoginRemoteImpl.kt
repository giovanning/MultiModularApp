package com.projetos.filmei.login.data.source

import com.projetos.filmei.data.error.toDomain
import com.projetos.filmei.data.result.OutCome
import com.projetos.filmei.data.source.NetworkDataSource
import com.projetos.filmei.login.data.request.LoginRequestBody
import com.projetos.filmei.login.data.service.LoginService
import com.projetos.filmei.login.domain.mapper.LoginMapper
import com.projetos.filmei.login.domain.model.User

class LoginRemoteImpl(
    private val networkDataSource: NetworkDataSource<LoginService>,
    private val loginMapper: LoginMapper,
) : LoginRemote {
    override suspend fun login(loginRequestBody: LoginRequestBody): OutCome<User> {
        return networkDataSource.performRequest(
            request = { login(loginRequestBody).await() },
            onSuccess = { response, _ ->
                OutCome.success(loginMapper.toDomain(response))
            },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) },
        )
    }
}
