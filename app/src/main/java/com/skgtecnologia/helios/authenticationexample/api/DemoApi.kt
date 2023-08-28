package com.skgtecnologia.helios.authenticationexample.api

import com.skgtecnologia.helios.authenticationexample.api.model.DemoResponse
import retrofit2.http.GET

interface DemoApi {
    @GET("corridor-geom/format-geo-json")
    suspend fun getFormat(): DemoResponse
}