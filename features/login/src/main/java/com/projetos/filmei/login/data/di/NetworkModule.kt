package com.projetos.filmei.login.data.di

import com.google.gson.Gson
import com.projetos.filmei.data.constants.DISPATCHER_DEFAULT_TAG
import com.projetos.filmei.data.constants.USER_ID_TAG
import com.projetos.filmei.data.factory.ServiceFactory
import com.projetos.filmei.data.source.NetworkDataSource
import com.projetos.filmei.login.data.service.LoginService
import com.projetos.filmei.login.data.source.LoginRemote
import com.projetos.filmei.login.data.source.LoginRemoteImpl
import com.projetos.filmei.login.domain.mapper.LoginMapper
import com.projetos.filmei.login.domain.mapper.LoginMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoginServiceFactory(serviceFactory: ServiceFactory): LoginService {
        return serviceFactory.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(
        loginService: LoginService,
        gson: Gson,
        @Named(USER_ID_TAG) userIdProvider: () -> String,
    ): NetworkDataSource<LoginService> {
        return NetworkDataSource(loginService, gson, userIdProvider)
    }

    @Provides
    @Singleton
    fun provideLoginMapper(@Named(DISPATCHER_DEFAULT_TAG) coroutineDispatcher: CoroutineDispatcher): LoginMapper {
        return LoginMapperImpl(coroutineDispatcher)
    }

    @Provides
    @Singleton
    fun provideLoginRemoteImpl(
        networkDataSource: NetworkDataSource<LoginService>,
        loginMapper: LoginMapper,
    ): LoginRemote {
        return LoginRemoteImpl(networkDataSource, loginMapper)
    }
}
