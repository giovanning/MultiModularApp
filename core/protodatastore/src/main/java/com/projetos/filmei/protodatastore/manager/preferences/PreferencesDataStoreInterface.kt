package com.projetos.filmei.protodatastore.manager.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesDataStoreInterface {
    suspend fun setLanguage(language: String)
    suspend fun setAppLockEnabled(isAppLockEnabled: Boolean)
    suspend fun setNotificationCount(notificationCount: Int)
    suspend fun setMoneyBalance(moneyBalance: Long)

    suspend fun getLanguage(): String
    fun getLanguageFlow(): Flow<String>

    suspend fun isAppLockEnabled(): Boolean
    fun isAppLockEnabledFlow(): Flow<Boolean>

    suspend fun getNotificationCount(): Int
    fun getNotificationCountFlow(): Flow<Int>

    suspend fun getMoneyBalance(): Long
    fun getMoneyBalanceFlow(): Flow<Long>
}
