package com.skgtecnologia.helios.authenticationmodule.domain

import android.content.Intent
import android.net.Uri
import androidx.core.app.ActivityCompat.startActivityForResult
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues

interface AuthenticationUseCase {

    suspend fun authenticateWithPassword() {

    }
}
