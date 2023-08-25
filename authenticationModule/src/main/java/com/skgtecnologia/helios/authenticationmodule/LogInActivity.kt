package com.skgtecnologia.helios.authenticationmodule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.skgtecnologia.helios.authenticationmodule.domain.AuthenticationRouter
import com.skgtecnologia.helios.authenticationmodule.domain.AuthenticationTokenExchange
import com.skgtecnologia.helios.authenticationmodule.presentation.LogInScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.koin.android.ext.android.inject

class LogInActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginIntent: Intent by inject()
        val authenticationTokenExchange: AuthenticationTokenExchange by inject()
        val authenticationRouter: AuthenticationRouter by inject()
        val loadingFlow = MutableStateFlow(false)
        val getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                loadingFlow.update { true }
                val value = it.data!!
                authenticationTokenExchange.requestToken(value) {
                    authenticationRouter.onUserAuthenticated()
                }
            } else {
                Toast.makeText(this, "Intentelo otra vez", Toast.LENGTH_LONG).show()
            }
        }

        setContent {
            val loading = loadingFlow.collectAsState()
            if (loading.value) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize(0.1f),
                    )
                }
            } else {
                SecondaryLogInScreen() {
                    getResult.launch(loginIntent)
                }
            }
        }
    }
}

@Composable
fun SecondaryLogInScreen(onButtonClick: () -> Unit) {
    LogInScreen() {
        onButtonClick()
    }
}
