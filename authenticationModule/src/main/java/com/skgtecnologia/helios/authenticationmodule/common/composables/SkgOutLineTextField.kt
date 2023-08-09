package com.skgtecnologia.helios.authenticationmodule.common.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

object SkgTextInput {
    @Composable
    fun OutLinedTextField(
        textFieldModifier: Modifier = Modifier.fillMaxWidth(0.9f),
        @StringRes label: Int,
        keyboardOptions: KeyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        isSingleLine: Boolean = true,
        maxLine: Int = 1,
        isError: Boolean = false,
        onAction: () -> Unit = {},
        onTextChange: (String) -> Unit,
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        onTextChange(text)
        OutlinedTextField(
            value = text,
            singleLine = isSingleLine,
            maxLines = maxLine,
            isError = isError,
            label = { Text(text = stringResource(label)) },
            onValueChange = {
                if (it.length < 120) {
                    text = it
                }
            },
            keyboardActions = KeyboardActions(onDone = {
                onAction()
            }),
            keyboardOptions = keyboardOptions,
            modifier = textFieldModifier,
            shape = RoundedCornerShape(15.dp),
        )
    }

    @Composable
    fun OutLinedPasswordField(
        passwordFieldModifier: Modifier = Modifier.fillMaxWidth(0.9f),
        @StringRes label: Int,
        onTextChange: (String) -> Unit,
        isError: Boolean = false,
    ) {
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        onTextChange(password)
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(label)) },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                // TODO:Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            },
            isError = isError,
            modifier = passwordFieldModifier,
            shape = RoundedCornerShape(15.dp),
        )
    }
}
