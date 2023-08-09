package com.skgtecnologia.helios.authenticationmodule.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LogInInformationViewModel() : ViewModel() {

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var showError by mutableStateOf(false)

    fun onLogInButtonClickValidation(): Boolean {
        return isEmailValid(email) && password.isNotEmpty()
    }

    fun updateEmail(newEmail: String) {
        email = newEmail
        if (email.length > 16) {
            showError = !onLogInButtonClickValidation()
        }
    }

    fun updatePassWord(newPassword: String) {
        password = newPassword
        if (password.length > 4) {
            showError = !onLogInButtonClickValidation()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.matches(EMAIL_REGEX, email)
    }

    companion object {
        private const val EMAIL_REGEX =
            "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$"
    }
}
