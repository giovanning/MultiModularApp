package com.projetos.filmei.data.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.projetos.filmei.data.BuildConfig
import com.projetos.filmei.data.OkHttpClientProvider
import com.projetos.filmei.data.connectivity.NetworkMonitorInterface
import com.projetos.filmei.data.connectivity.NetworkMonitorInterfaceImpl
import com.projetos.filmei.data.constants.AUTHENTICATION_INTERCEPTOR_TAG
import com.projetos.filmei.data.constants.CHUCKER_INTERCEPTOR_TAG
import com.projetos.filmei.data.constants.HEADER_INTERCEPTOR_TAG
import com.projetos.filmei.data.constants.LOGGING_INTERCEPTOR_TAG
import com.projetos.filmei.data.factory.ServiceFactory
import com.projetos.filmei.data.okhttp.OkHttpClientProviderInterface
import com.projetos.filmei.data.session.SessionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providerGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providerNetworkMonitorInterface(context: Context): NetworkMonitorInterface {
        return NetworkMonitorInterfaceImpl(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClientProvider(): OkHttpClientProviderInterface {
        return OkHttpClientProvider()
    }

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(
        @Named(LOGGING_INTERCEPTOR_TAG) interceptor: Interceptor,
        @Named(HEADER_INTERCEPTOR_TAG) headerInterceptor: Interceptor,
        @Named(CHUCKER_INTERCEPTOR_TAG) chuckerInterceptor: Interceptor,
        @Named(AUTHENTICATION_INTERCEPTOR_TAG) authenticationInterceptor: Interceptor,
        okHttpClientProvider: OkHttpClientProviderInterface,
    ): OkHttpClient {
        return okHttpClientProvider.getOkHttpClient(BuildConfig.PIN_CERTIFICATE)
            .addInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(authenticationInterceptor)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followSslRedirects(false)
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okhttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl("")
            .client(okhttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideServiceFactory(retrofit: Retrofit): ServiceFactory {
        return ServiceFactory(retrofit)
    }

    @Provides
    @Singleton
    fun provideSessionService(serviceFactory: ServiceFactory): SessionService {
        return serviceFactory.create(SessionService::class.java)
    }
}
