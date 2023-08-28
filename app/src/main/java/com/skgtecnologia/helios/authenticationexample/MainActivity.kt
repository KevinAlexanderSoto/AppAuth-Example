package com.skgtecnologia.helios.authenticationexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skgtecnologia.helios.authenticationexample.api.DemoApi
import com.skgtecnologia.helios.authenticationexample.network.NetworkCallHandler
import com.skgtecnologia.helios.authenticationexample.ui.theme.AuthenticationExampleTheme
import com.skgtecnologia.helios.authenticationmodule.domain.AuthStateManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authStateManager: AuthStateManager by inject()
        val networkCallHandler: NetworkCallHandler by inject()
        val demoApi: DemoApi by inject()
        authStateManager.restoreState()
        setContent {
            AuthenticationExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val token = authStateManager.currentToken.collectAsState()
                    Column {
//
                        Greeting("Kevin")
                        // Text(text = token.value)

                        Button(onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                networkCallHandler.makeNetworkCall {
                                    try {
                                        demoApi.getFormat()
                                    } catch (e: Exception) {
                                    }
                                }.collectLatest {
                                }
                            }
                        }) {
                            Text(text = "Make network token request")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AuthenticationExampleTheme {
        Greeting("Android")
    }
}
