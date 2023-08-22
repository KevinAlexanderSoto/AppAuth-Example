package com.skgtecnologia.helios.authenticationmodule.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skgtecnologia.helios.authenticationmodule.R
import com.skgtecnologia.helios.authenticationmodule.common.composables.SkgButton

@Composable
fun LogInScreen(
    onLogInClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(R.drawable.background_image), // TODO: Add configuration
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
        Image(
            painter = painterResource(R.drawable.bg_city_landscape), // TODO: Add configuration
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth(),
        )

        // Other composable can be added on top of the background image
        Card(
            modifier = Modifier.fillMaxWidth(0.95f).height(IntrinsicSize.Min)
                .align(Alignment.Center),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.White, // TODO: Add configuration
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // LogInCardTitle()
                Text(
                    "Gestion de recursos en campo", // TODO: Add configuration
                    Modifier.padding(2.dp),
                    fontSize = 20.sp,
                )
                Text(
                    text = "Porfavor inicia sesión para empezar", // TODO: Add configuration
                    modifier = Modifier.padding(8.dp),
                ) // TODO: Add configuration

                SkgButton.ButtonPlusIcon(
                    isEnable = true,
                    buttonColor = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF56CFFF)),
                    onClick = {
                        onLogInClick()
                    },
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.bg_smart_helios_logo), // TODO: Add configuration
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxSize(0.2f),
        )
    }
}
