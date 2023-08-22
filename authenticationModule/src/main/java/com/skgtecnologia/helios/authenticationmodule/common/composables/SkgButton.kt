package com.skgtecnologia.helios.authenticationmodule.common.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object SkgButton {
    @Composable
    fun ButtonPlusIcon(
        isEnable: Boolean,
        onClick: () -> Unit,
        buttonColor: ButtonColors = ButtonDefaults.buttonColors(),
    ) {
        Button(
            onClick = {
                onClick.invoke()
            },
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(0.8f),
            shape = RoundedCornerShape(23.dp),
            contentPadding = PaddingValues(8.dp),
            enabled = isEnable,
            colors = buttonColor,
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos, // TODO: Add configuration
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = "Ingresar", // TODO: Add configuration
                fontSize = 20.sp, // TODO: Add configuration
            )
        }
    }

    @Composable
    fun CircleButton(
        isEnable: Boolean = true,
        onClick: () -> Unit,
    ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier.size(50.dp),
            enabled = isEnable,
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Blue),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue),
        ) {
            Icon(Icons.Default.Add, contentDescription = "content description")
        }
    }
}
