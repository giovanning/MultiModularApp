package com.projetos.filmei.protodatastore.manager.session

import com.projetos.filmei.proto.Session
import kotlinx.coroutines.flow.Flow

interface SessionDataStoreInterface {
    suspend fun setAccessToken(accessToken: String)

    suspend fun setRefreshToken(refreshToken: String)

    suspend fun setUserId(userId: String)

    suspend fun setSession(accessToken: String, refreshToken: String, userId: String)

    suspend fun getAccessToken(): String
    fun getAccessTokenFlow(): Flow<String>

    suspend fun getRefreshToken(): String
    fun getRefreshTokenFlow(): Flow<String>

    suspend fun getUserId(): String
    fun getUserIdFlow(): Flow<String>

    suspend fun getSession(): Session
    fun getSessionFlow(): Flow<Session>
}
