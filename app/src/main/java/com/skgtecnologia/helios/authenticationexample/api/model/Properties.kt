package com.skgtecnologia.helios.authenticationexample.api.model

data class Properties(
    val composedName: String,
    val distance: Int,
    val id: Int,
    val nameFrom: String,
    val nameTo: String,
    val orient: String,
    val readTime: String,
    val speed: Double,
    val time: String,
    val totalIncidents: Int
)