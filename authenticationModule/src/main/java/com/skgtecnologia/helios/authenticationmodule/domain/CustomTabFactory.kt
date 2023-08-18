package com.skgtecnologia.helios.authenticationmodule.domain

import androidx.browser.customtabs.CustomTabsIntent

interface CustomTabFactory {

    fun getCustomTabIntent(): CustomTabsIntent
}
