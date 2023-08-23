package com.skgtecnologia.helios.authenticationmodule.domain

import android.content.Intent
import android.net.Uri
import com.skgtecnologia.helios.authenticationmodule.AuthenticationConfiguration
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues

/**
 * this class is responsible for providing an intent for authentication.This Intent will be used for AppAuth to launch a customTabs
 *
 * The class has several properties, such as  authorizationService ,  baseTabFactory ,  serviceConfig ,  clientId ,  redirectUri , and  authorizationBuilder .
 *
 * The  serviceConfig  property is an instance of  AuthorizationServiceConfiguration  that is initialized with two URIs representing the authorization endpoint and the token endpoint.
 *
 * The  redirectUri  property is an instance of  Uri  representing the redirect URI.
 *
 * The  authorizationBuilder  property is an instance of  AuthorizationRequest.Builder  that is initialized with the  serviceConfig ,  clientId ,  ResponseTypeValues.CODE , and  redirectUri .
 *
 * The  getAuthenticationIntent  function sets the scopes to  SCOPE_KEY_CLOAK , builds an authorization request using the  authorizationBuilder , and returns the intent obtained from the  authorizationService  by passing the authorization request and the intent obtained from  baseTabFactory .
 *@author Kevin Penaranda
 * **/
class AuthenticationRequestIntentProvider(
    private val authorizationService: AuthorizationService,
    private val baseTabFactory: CustomTabFactory,
    private val authenticationConfiguration: AuthenticationConfiguration,
) {
    private val serviceConfig = AuthorizationServiceConfiguration(
        Uri.parse(authenticationConfiguration.authorizationUrl), // authorization endpoint
        Uri.parse(authenticationConfiguration.tokenExchangeUrl), // token endpoint
    )
    private val redirectUri = Uri.parse(URL_AUTH_REDIRECT)
    private val authorizationBuilder = AuthorizationRequest.Builder(
        serviceConfig,
        authenticationConfiguration.clientId,
        ResponseTypeValues.CODE,
        redirectUri,
    )

    fun getAuthenticationIntent(): Intent {
        authorizationBuilder.setScopes(authenticationConfiguration.keyCloakScope)
        val authRequest = authorizationBuilder.build()
        return authorizationService.getAuthorizationRequestIntent(
            authRequest,
            baseTabFactory.getCustomTabIntent(),
        )
    }

    companion object {
        const val SHARED_PREFERENCES_NAME = "AUTH_STATE_PREFERENCE"
        const val AUTH_STATE = "AUTH_STATE"
        const val URL_AUTH_REDIRECT =
            "com.skgtecnologia.helios.authenticationmodule:/oauth2redirect"
    }
}
