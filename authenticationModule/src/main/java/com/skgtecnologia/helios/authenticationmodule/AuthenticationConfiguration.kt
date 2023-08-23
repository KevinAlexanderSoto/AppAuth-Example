package com.skgtecnologia.helios.authenticationmodule

class AuthenticationConfiguration private constructor(
    val keyCloakScope: String,
    val clientId: String,
    val authorizationUrl: String,
    val tokenExchangeUrl: String,

) {
    class Builder {
        @set:JvmSynthetic
        var keyCloakScope: String = Companion.keyCloakScope

        @set:JvmSynthetic
        var clientId: String = Companion.clientId

        @set:JvmSynthetic
        var authorizationUrl: String = Companion.authorizationUrl

        @set:JvmSynthetic
        var tokenExchangeUrl: String = Companion.tokenExchangeUrl
        fun build() =
            AuthenticationConfiguration(
                keyCloakScope,
                clientId,
                authorizationUrl,
                tokenExchangeUrl,
            )
    }

    internal companion object {
        const val keyCloakScope = "openid profile email"
        const val clientId = "smm-qa-test"
        const val authorizationUrl =
            "https://keycloak.sitbogota.com/auth/realms/smm-qa-env/protocol/openid-connect/auth"
        const val tokenExchangeUrl =
            "https://keycloak.sitbogota.com/auth/realms/smm-qa-env/protocol/openid-connect/token"
    }
}

@Suppress("FunctionName")
@JvmSynthetic
fun AuthenticationConfiguration(initializer: AuthenticationConfiguration.Builder.() -> Unit): AuthenticationConfiguration =
    AuthenticationConfiguration.Builder().apply(initializer).build()
