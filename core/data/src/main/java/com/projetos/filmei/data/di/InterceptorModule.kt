package com.projetos.filmei.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.projetos.filmei.data.BuildConfig
import com.projetos.filmei.data.connectivity.NetworkMonitorInterface
import com.projetos.filmei.data.constants.AUTHENTICATION_INTERCEPTOR_TAG
import com.projetos.filmei.data.constants.CHUCKER_INTERCEPTOR_TAG
import com.projetos.filmei.data.constants.CLIENT_ID_TAG
import com.projetos.filmei.data.constants.CONNECTIVITY_INTERCEPTOR_TAG
import com.projetos.filmei.data.constants.DISPATCHER_IO_TAG
import com.projetos.filmei.data.constants.HEADER_INTERCEPTOR_TAG
import com.projetos.filmei.data.constants.LANGUAGE_TAG
import com.projetos.filmei.data.constants.LOGGING_INTERCEPTOR_TAG
import com.projetos.filmei.data.interceptors.AUTHORIZATION_HEADER
import com.projetos.filmei.data.interceptors.AuthenticationInterceptor
import com.projetos.filmei.data.interceptors.CLIENT_ID_HEADER
import com.projetos.filmei.data.interceptors.ConnectivityInterceptor
import com.projetos.filmei.data.interceptors.HeaderInterceptor
import com.projetos.filmei.protodatastore.manager.session.SessionDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    @Provides
    @Singleton
    @Named(HEADER_INTERCEPTOR_TAG)
    fun provideHeaderInterceptor(
        @Named(CLIENT_ID_TAG) clientId: String,
        @Named(LANGUAGE_TAG) language: () -> Locale,
    ): Interceptor {
        return HeaderInterceptor(
            clientId = clientId,
            languageProvider = language,
        )
    }

    @Provides
    @Singleton
    @Named(AUTHENTICATION_INTERCEPTOR_TAG)
    fun provideAutheticationInterceptor(
        sessionDataStoreInterface: SessionDataStoreInterface,
        @Named(DISPATCHER_IO_TAG) coroutinerDispatcher: CoroutineDispatcher,
    ): Interceptor {
        return AuthenticationInterceptor(
            sessionDataStoreInterface,
            coroutinerDispatcher,
        )
    }

    @Provides
    @Singleton
    @Named(CHUCKER_INTERCEPTOR_TAG)
    fun provideChuckerInterceptor(@ApplicationContext context: Context): Interceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(
                ChuckerCollector(
                    context = context,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR,
                ),
            )
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(250_000L)
            // List of headers to replace with ** in the Chucker UI
            .redactHeaders(AUTHORIZATION_HEADER)
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            // Controls Android shortcut creation.
            .createShortcut(true)
            .build()
    }

    @Provides
    @Singleton
    @Named(CONNECTIVITY_INTERCEPTOR_TAG)
    fun provideConnectivityInterceptor(networkMonitorInterface: NetworkMonitorInterface): Interceptor {
        return ConnectivityInterceptor(networkMonitorInterface)
    }

    @Provides
    @Singleton
    @Named(LOGGING_INTERCEPTOR_TAG)
    fun provideOkHttpLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        if (!BuildConfig.DEBUG) {
            interceptor.redactHeader(CLIENT_ID_HEADER)
            interceptor.redactHeader(AUTHORIZATION_HEADER) // redact any header that contains sensitive information
        }
        return interceptor
    }
}
