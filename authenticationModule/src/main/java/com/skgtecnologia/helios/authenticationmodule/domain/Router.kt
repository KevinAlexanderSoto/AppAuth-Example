package com.skgtecnologia.helios.authenticationmodule.domain
/**
 * Implement this interface to navigate to other activity when the user authenticate success
 * Is mandatory to have this implemented in order to have the library working
 * @author Kevin Penaranda
 * **/
interface Router {

    fun onUserAuthenticated()
}
