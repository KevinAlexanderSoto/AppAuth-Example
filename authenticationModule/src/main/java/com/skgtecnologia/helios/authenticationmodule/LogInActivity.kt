package com.skgtecnologia.helios.authenticationmodule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.skgtecnologia.helios.authenticationmodule.domain.AuthenticationTokenExchange
import com.skgtecnologia.helios.authenticationmodule.domain.Router
import com.skgtecnologia.helios.authenticationmodule.presentation.LogInScreen
import org.koin.android.ext.android.inject

class LogInActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginIntent: Intent by inject()
        val authenticationTokenExchange: AuthenticationTokenExchange by inject()
        val authenticationRouter: Router by inject()
        val showHelperScreen = mutableStateOf(false)
        val getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                showHelperScreen.value = false
                Toast.makeText(this, "Authentication DONE!!", Toast.LENGTH_LONG).show()
                val value = it.data!!
                authenticationTokenExchange.requestToken(value) {
                    authenticationRouter.OnUserAuthenticated()
                }
            } else {
                Toast.makeText(this, "Authentication FAILURE!!", Toast.LENGTH_LONG).show()
                showHelperScreen.value = true
            }
        }

        setContent {
            LaunchedEffect(key1 = Unit, block = {
                getResult.launch(loginIntent)
            })
            AnimatedVisibility(visible = showHelperScreen.value) {
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
