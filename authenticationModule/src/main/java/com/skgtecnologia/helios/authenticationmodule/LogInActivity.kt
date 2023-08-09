package com.skgtecnologia.helios.authenticationmodule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.skgtecnologia.helios.authenticationmodule.presentation.LogInScreen
import org.koin.android.ext.android.inject

class LogInActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val LogInIntent: Intent by inject()
        val getResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult(),
            ) {
                if (it.resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Authentication DONE!!", Toast.LENGTH_LONG).show()
                    val value = it.data?.getStringExtra("input")
                } else {
                    Toast.makeText(this, "Authentication FAILURE!!", Toast.LENGTH_LONG).show()
                }
            }
        setContent {
            LogInScreen() {
                getResult.launch(LogInIntent)
            }
        }
    }

    fun onUserAuthenticated() {
    }
}
