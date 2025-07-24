package com.projetos.filmei.login.data.service

import com.projetos.filmei.login.data.request.LoginRequestBody
import com.projetos.filmei.login.data.response.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://mydomain.com"
const val EMAIL = "email"

interface LoginService {
    @POST("$BASE_URL/auth/login")
    fun login(@Body loginRequestBody: LoginRequestBody): Deferred<Response<UserResponse>>

    @POST("$BASE_URL/auth/forget-password")
    fun forgetPassword(@Query(EMAIL) email: String): Deferred<Response<Unit>>
}
