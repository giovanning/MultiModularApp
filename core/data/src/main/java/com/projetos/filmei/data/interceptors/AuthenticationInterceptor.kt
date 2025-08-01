package com.projetos.filmei.data.interceptors

import com.projetos.filmei.data.response.TokenResponse
import com.projetos.filmei.data.session.SessionService
import com.projetos.filmei.data.source.DataSource.Companion.UNAUTHORISED
import com.projetos.filmei.protodatastore.manager.session.SessionDataStoreInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val sessionDataStoreInterface: SessionDataStoreInterface,
    private val coroutineDispatcher: CoroutineDispatcher,
) : Interceptor {

    @Inject
    lateinit var sessionService: SessionService

    private val mutex = Mutex()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val accessToken =
            runBlocking(coroutineDispatcher) { sessionDataStoreInterface.getAccessToken() }

        val authenticatedRequest =
            request.newBuilder().header(AUTHORIZATION_HEADER, "$BEARER $accessToken").build()

        val response = chain.proceed(authenticatedRequest)

        if (response.code != UNAUTHORISED) {
            // your access token is valid you can resume hitting APIs
            return response
        }

        // Token is un authorized so try to refresh your access token and refresh token

        val tokenResponse: TokenResponse? = runBlocking {
            mutex.withLock {
                val tokenResponse = getUpdatedToken().await()
                tokenResponse.body().also {
                    sessionDataStoreInterface.setAccessToken(it?.accessToken ?: "")
                    sessionDataStoreInterface.setRefreshToken(it?.refreshToken ?: "")
                }
            }
        }

        return if (tokenResponse?.accessToken != null) {
            response.close()

            // retry the original request with the new token
            val authenticatedFreshRequest =
                request.newBuilder()
                    .header(AUTHORIZATION_HEADER, "Bearer ${tokenResponse.accessToken}").build()

            chain.proceed(authenticatedFreshRequest)
        } else {
            response
        }
    }

    private suspend fun getUpdatedToken(): Deferred<retrofit2.Response<TokenResponse>> {
        val refreshToken = sessionDataStoreInterface.getRefreshToken()
        return withContext(coroutineDispatcher) {
            sessionService.getTokens(refreshToken)
        }
    }
}
