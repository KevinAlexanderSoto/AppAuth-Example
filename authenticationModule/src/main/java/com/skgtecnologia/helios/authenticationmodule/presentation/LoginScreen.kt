package com.skgtecnologia.helios.authenticationmodule.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skgtecnologia.helios.authenticationmodule.R
import com.skgtecnologia.helios.authenticationmodule.common.composables.SkgButton
import com.skgtecnologia.helios.authenticationmodule.common.composables.SkgTextInput
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogInScreen(
    informationViewModel: LogInInformationViewModel = koinViewModel(),
    onLogInClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(R.drawable.background_image), // TODO: Add configuration
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        // Other composable can be added on top of the background image
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(IntrinsicSize.Min)
                .align(Alignment.Center),
            backgroundColor = Color.White, // TODO: Add configuration
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LogInCardTitle()
                SkgTextInput.OutLinedTextField(
                    label = R.string.email_text_field_label, // TODO: Add configuration
                    onTextChange = {
                        informationViewModel.updateEmail(it)
                    },
                    isError = informationViewModel.showError,
                )
                SkgTextInput.OutLinedPasswordField(
                    label = R.string.email_text_field_label, // TODO: Add configuration
                    onTextChange = {
                        informationViewModel.updatePassWord(it)
                    },
                    isError = informationViewModel.showError,
                )
                SkgButton.ButtonPlusIcon(isEnable = true) {
                    onLogInClick()
                    if (informationViewModel.onLogInButtonClickValidation()) {
                        // TODO: Handle this action
                    } else {
                        informationViewModel.showError = true
                    }
                }
                Text(text = "O ingresa con") // TODO: Add configuration
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SkgButton.CircleButton {
                    }
                    SkgButton.CircleButton {
                    }
                }
            }
        }
    }
}

@Composable
fun LogInCardTitle() {
    Row(
        modifier = Modifier.height(IntrinsicSize.Min).fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ) {
        Divider(
            color = Color.Black, // TODO: Add configuration
            modifier = Modifier
                .fillMaxHeight()
                .width(6.dp),
        )
        Column {
            Text("Sit Bogota", Modifier.padding(2.dp)) // TODO: Add configuration

            Text(
                "Sistema inteligente de transporte", // TODO: Add configuration
                Modifier.padding(2.dp),
            ) // TODO: Add configuration
        }
    }
}

@Preview
@Composable
fun preview() {
 //   LogInScreen()
}
