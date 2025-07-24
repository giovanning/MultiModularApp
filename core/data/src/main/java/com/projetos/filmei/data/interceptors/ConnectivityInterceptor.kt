package com.projetos.filmei.data.interceptors

import com.projetos.filmei.data.connectivity.NetworkMonitorInterface
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val networkMonitorInterface: NetworkMonitorInterface) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (networkMonitorInterface.hasConnectivity()) {
            return chain.proceed(chain.request())
        } else {
            throw NoConnectivityException
        }
    }
}

object NoConnectivityException : IOException() {
    private fun readResolve(): Any = NoConnectivityException
}
