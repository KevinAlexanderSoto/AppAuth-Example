package com.skgtecnologia.helios.authenticationexample.api

import com.skgtecnologia.helios.authenticationexample.network.ApiNetworkInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val DemoApiModule = module {
    single<DemoApi> {
        Retrofit.Builder()
            .baseUrl("https://gateway.sitbogota.com/traffic/api/v1/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(DemoApi::class.java)
    }
    single<OkHttpClient> {
        OkHttpClient
            .Builder()
            .addInterceptor(ApiNetworkInterceptor)
            .build()
    }
}
