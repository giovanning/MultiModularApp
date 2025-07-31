package br.com.siag.navigator.di

import br.com.siag.navigator.core.AppNavigator
import br.com.siag.navigator.core.AppNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {
    @Provides
    @Singleton
    fun provideNavigator(): AppNavigator {
        return AppNavigatorImpl()
    }
}
