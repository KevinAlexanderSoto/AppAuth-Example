package com.skgtecnologia.helios.authenticationexample.api.model

data class DemoResponse(
    val currentPage: Int,
    val features: List<Feature>,
    val totalPages: Int,
    val totalRecords: Int,
    val type: String
)