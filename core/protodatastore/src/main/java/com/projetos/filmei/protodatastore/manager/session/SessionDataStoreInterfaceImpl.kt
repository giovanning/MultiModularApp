package com.projetos.filmei.protodatastore.manager.session

import androidx.datastore.core.DataStore
import com.projetos.filmei.proto.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SessionDataStoreInterfaceImpl(private val sessionDataStore: DataStore<Session>) :
    SessionDataStoreInterface {
    override suspend fun setAccessToken(accessToken: String) {
        sessionDataStore.updateData { currentSession ->
            currentSession.toBuilder().setAccessToken(accessToken).build()
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        sessionDataStore.updateData { currentSession ->
            currentSession.toBuilder().setRefreshToken(refreshToken).build()
        }
    }

    override suspend fun setUserId(userId: String) {
        sessionDataStore.updateData { currentSession ->
            currentSession.toBuilder().setUserId(userId).build()
        }
    }

    override suspend fun setSession(accessToken: String, refreshToken: String, userId: String) {
        sessionDataStore.updateData { currentSession ->
            currentSession.toBuilder()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setUserId(userId)
                .build()
        }
    }

    override suspend fun getAccessToken(): String {
        return sessionDataStore.data.first().accessToken
    }

    override fun getAccessTokenFlow(): Flow<String> {
        return sessionDataStore.data.map { session ->
            session.accessToken
        }
    }

    override suspend fun getRefreshToken(): String {
        return sessionDataStore.data.first().refreshToken
    }

    override fun getRefreshTokenFlow(): Flow<String> {
        return sessionDataStore.data.map { session ->
            session.refreshToken
        }
    }

    override suspend fun getUserId(): String {
        return sessionDataStore.data.first().userId
    }

    override fun getUserIdFlow(): Flow<String> {
        return sessionDataStore.data.map { session ->
            session.userId
        }
    }

    override suspend fun getSession(): Session {
        return sessionDataStore.data.first()
    }

    override fun getSessionFlow(): Flow<Session> {
        return sessionDataStore.data.map {
            it
        }
    }
}
