package com.skgtecnologia.helios.authenticationexample

import android.app.Application
import android.content.Intent
import com.skgtecnologia.helios.authenticationmodule.domain.Router

class AuthenticationRouterImpl(
    private val context: Application,
) : Router {
    override fun OnUserAuthenticated() {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}
