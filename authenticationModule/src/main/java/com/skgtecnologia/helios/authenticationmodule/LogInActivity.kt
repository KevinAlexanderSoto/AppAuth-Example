package com.skgtecnologia.helios.authenticationmodule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
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
        val getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data!!
                authenticationTokenExchange.requestToken(value) {
                    authenticationRouter.onUserAuthenticated()
                }
            } else {
                Toast.makeText(this, "Intentelo otra vez", Toast.LENGTH_LONG).show()
            }
        }

        setContent {
            SecondaryLogInScreen() {
                getResult.launch(loginIntent)
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
