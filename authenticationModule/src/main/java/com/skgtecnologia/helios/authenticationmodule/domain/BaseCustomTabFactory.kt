package com.skgtecnologia.helios.authenticationmodule.domain

import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import net.openid.appauth.AuthorizationService

/**
 * The class overrides the "getCustomTabIntent()" method from the "CustomTabFactory" interface.
 * This method returns a "CustomTabsIntent" object.
 *
 * Inside the method, it uses the "authorizationService" to create a custom tabs intent builder.
 * It sets the default color scheme parameters using a builder, with the toolbar color set to a dark color.
 *
 * It enables URL bar hiding, hides the title, and sets the share state to off. Finally, it builds and returns the custom tabs intent.
 *
 * @author Kevin Penaranda
 * **/
class BaseCustomTabFactory(
    private val authorizationService: AuthorizationService,
) : CustomTabFactory {
    override fun getCustomTabIntent(): CustomTabsIntent {
        return authorizationService.createCustomTabsIntentBuilder().setDefaultColorSchemeParams(
            CustomTabColorSchemeParams.Builder()
                .setToolbarColor(0xFF19202E.toInt())
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
