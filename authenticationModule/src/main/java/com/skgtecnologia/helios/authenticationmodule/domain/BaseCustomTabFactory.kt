package com.skgtecnologia.helios.authenticationmodule.domain

import android.R
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import net.openid.appauth.AuthorizationService

class BaseCustomTabFactory(
    private val authorizationService: AuthorizationService,
) : CustomTabFactory {
    override fun getCustomTabIntent(): CustomTabsIntent {
        return authorizationService.createCustomTabsIntentBuilder().setDefaultColorSchemeParams(
            CustomTabColorSchemeParams.Builder()
                .setToolbarColor(android.R.attr.colorPrimaryDark)
                .build(),
        ) // set the alternative dark color scheme
            .setColorSchemeParams(
                CustomTabsIntent.COLOR_SCHEME_DARK,
                CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(android.R.attr.colorPrimaryDark)
                    .build(),
            )
            .setUrlBarHidingEnabled(true)
            .setShowTitle(false)
            .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
            .build()
    }
}
