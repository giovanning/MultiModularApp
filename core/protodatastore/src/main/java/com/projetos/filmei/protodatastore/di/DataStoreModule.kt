package com.projetos.filmei.protodatastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.projetos.filmei.proto.Preferences
import com.projetos.filmei.proto.Session
import com.projetos.filmei.protodatastore.factory.preferencesDataStore
import com.projetos.filmei.protodatastore.factory.sessionDataStore
import com.projetos.filmei.protodatastore.manager.preferences.PreferencesDataStoreInterface
import com.projetos.filmei.protodatastore.manager.preferences.PreferencesDataStoreInterfaceImpl
import com.projetos.filmei.protodatastore.manager.session.SessionDataStoreInterface
import com.projetos.filmei.protodatastore.manager.session.SessionDataStoreInterfaceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {
        return context.preferencesDataStore
    }

    @Provides
    @Singleton
    fun provideSessionDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Session> {
        return context.sessionDataStore
    }

    @Provides
    @Singleton
    fun provideSessionStoreManager(
        sessionDataStore: DataStore<Session>,
    ): SessionDataStoreInterface {
        return SessionDataStoreInterfaceImpl(sessionDataStore)
    }

    @Provides
    @Singleton
    fun providePreferencesStoreManager(
        preferencesDataStore: DataStore<Preferences>,
    ): PreferencesDataStoreInterface {
        return PreferencesDataStoreInterfaceImpl(preferencesDataStore)
    }
}
