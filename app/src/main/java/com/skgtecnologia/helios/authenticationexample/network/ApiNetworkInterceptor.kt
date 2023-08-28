package com.skgtecnologia.helios.authenticationexample.network

import okhttp3.Interceptor
import okhttp3.Response

object ApiNetworkInterceptor : Interceptor {
    private const val AUTH_TOKEN_KEY = "Authorization"

    private var currentToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        if (currentToken == null) throw java.lang.RuntimeException("Need to be authenticated")
        requestBuilder.addHeader(AUTH_TOKEN_KEY, "Bearer " + currentToken!!)

        return chain.proceed(requestBuilder.build())
    }

    fun setCurrentToken(token: String?) {
        currentToken = token
    }
}
