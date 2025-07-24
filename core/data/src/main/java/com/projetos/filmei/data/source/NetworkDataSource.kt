package com.projetos.filmei.data.source

import android.util.Log
import com.google.gson.Gson
import com.projetos.filmei.data.connectivity.NetworkMonitorInterface
import com.projetos.filmei.data.constants.HEADER_LOCATION
import com.projetos.filmei.data.error.getDefaultErrorResponse
import com.projetos.filmei.data.error.getErrorResponse
import com.projetos.filmei.data.error.toDomain
import com.projetos.filmei.data.response.ErrorResponse
import com.projetos.filmei.data.result.OutCome
import com.projetos.filmei.data.source.DataSource.Companion.NO_INTERNET
import com.projetos.filmei.data.source.DataSource.Companion.SEE_OTHERS
import com.projetos.filmei.data.source.DataSource.Companion.SSL_PINNING
import com.projetos.filmei.data.source.DataSource.Companion.TIMEOUT
import com.projetos.filmei.data.source.DataSource.Companion.UNKNOWN
import kotlinx.coroutines.isActive
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException
import kotlin.coroutines.coroutineContext

class NetworkDataSource<SERVICE>(
    private val service: SERVICE,
    private val gson: Gson,
    private val networkMonitorInterface: NetworkMonitorInterface,
    private val userIdProvider: () -> String,
) {
    suspend fun <R, T> performRequest(
        request: suspend SERVICE.(String) -> Response<R>,
        onSuccess: suspend (R, okhttp3.Headers) -> OutCome<T> = { _, _ -> OutCome.empty() },
        onRedirect: suspend (String, Int) -> OutCome<T> = { _, _ -> OutCome.empty() },
        onEmpty: suspend () -> OutCome<T> = { OutCome.empty() },
        onError: suspend (ErrorResponse, Int) -> OutCome<T> = { errorResponse, code ->
            OutCome.error(errorResponse.toDomain(code))
        },
    ): OutCome<T> {
        if (!networkMonitorInterface.hasConnectivity()) {
            return onError(getDefaultErrorResponse(), NO_INTERNET)
        }

        return try {
            val response = service.request(userIdProvider())
            val responseCode = response.code()

            when {
                response.isSuccessful -> {
                    val body = response.body()
                    if (body != null && body != Unit) {
                        if (coroutineContext.isActive) {
                            onSuccess(body, response.headers())
                        } else {
                            onEmpty()
                        }
                    } else {
                        onEmpty()
                    }
                }
                responseCode == SEE_OTHERS -> {
                    val location = response.headers()[HEADER_LOCATION]
                    if (location != null) {
                        onRedirect(location, responseCode)
                    } else {
                        onError(getDefaultErrorResponse(), responseCode)
                    }
                }
                else -> {
                    val errorBody = response.errorBody()?.string()
                    if (errorBody.isNullOrBlank()) {
                        onError(getDefaultErrorResponse(), responseCode)
                    } else {
                        onError(getErrorResponse(gson, errorBody), responseCode)
                    }
                }
            }
        } catch (e: Exception) {
            handleException(e, onError)
        }
    }

    private suspend fun <T> handleException(
        e: Exception,
        onError: suspend (ErrorResponse, Int) -> OutCome<T>,
    ): OutCome<T> {
        val errorCode = when (e) {
            is SocketTimeoutException -> TIMEOUT
            is UnknownHostException -> NO_INTERNET
            is SSLPeerUnverifiedException, is SSLHandshakeException -> SSL_PINNING
            else -> UNKNOWN
        }
        Log.e("NetworkDataSource", "Network request failed", e)
        return onError(getDefaultErrorResponse(), errorCode)
    }
}
