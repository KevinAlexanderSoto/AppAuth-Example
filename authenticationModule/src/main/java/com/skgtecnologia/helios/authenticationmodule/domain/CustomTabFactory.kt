package com.skgtecnologia.helios.authenticationmodule.domain

import androidx.browser.customtabs.CustomTabsIntent

/**
 * Implementing this interface you will be available to set your CustomTab configuration.
 * this is provided in DI
 * @author Kevin Peñaranda
 * **/
interface CustomTabFactory {

    fun getCustomTabIntent(): CustomTabsIntent
}
