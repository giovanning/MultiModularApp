package com.projetos.filmei.protodatastore.manager.preferences

import androidx.datastore.core.DataStore
import com.projetos.filmei.proto.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesDataStoreInterfaceImpl(private val preferencesDataStore: DataStore<Preferences>) :
    PreferencesDataStoreInterface {
    override suspend fun setLanguage(language: String) {
        preferencesDataStore.updateData { currentPreferences ->
            currentPreferences.toBuilder().setLanguage(language).build()
        }
    }

    override suspend fun setAppLockEnabled(isAppLockEnabled: Boolean) {
        preferencesDataStore.updateData { currentPreferences ->
            currentPreferences.toBuilder().setIsAppLockEnabled(isAppLockEnabled).build()
        }
    }

    override suspend fun setNotificationCount(notificationCount: Int) {
        preferencesDataStore.updateData { currentPreferences ->
            currentPreferences.toBuilder().setNotificationCount(notificationCount).build()
        }
    }

    override suspend fun setMoneyBalance(moneyBalance: Long) {
        preferencesDataStore.updateData { currentPreferences ->
            currentPreferences.toBuilder().setMoneyBalance(moneyBalance).build()
        }
    }

    override suspend fun getLanguage(): String {
        return preferencesDataStore.data.first().language
    }

    override fun getLanguageFlow(): Flow<String> {
        return preferencesDataStore.data.map { preferences ->
            preferences.language
        }
    }

    override suspend fun isAppLockEnabled(): Boolean {
        return preferencesDataStore.data.first().isAppLockEnabled
    }

    override fun isAppLockEnabledFlow(): Flow<Boolean> {
        return preferencesDataStore.data.map { preferences ->
            preferences.isAppLockEnabled
        }
    }

    override suspend fun getNotificationCount(): Int {
        return preferencesDataStore.data.first().notificationCount
    }

    override fun getNotificationCountFlow(): Flow<Int> {
        return preferencesDataStore.data.map { preferences ->
            preferences.notificationCount
        }
    }

    override suspend fun getMoneyBalance(): Long {
        return preferencesDataStore.data.first().moneyBalance
    }

    override fun getMoneyBalanceFlow(): Flow<Long> {
        return preferencesDataStore.data.map { preferences ->
            preferences.moneyBalance
        }
    }
}
