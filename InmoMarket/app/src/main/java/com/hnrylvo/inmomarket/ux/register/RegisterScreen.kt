package com.hnrylvo.inmomarket.ux.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.compose.buttons.PrimaryButton
import com.hnrylvo.inmomarket.ui.compose.containers.AppScaffold
import com.hnrylvo.inmomarket.ui.compose.headers.LoginHeader
import com.hnrylvo.inmomarket.ui.compose.inputs.InputField
import com.hnrylvo.inmomarket.ui.theme.MyTypography
import com.hnrylvo.inmomarket.ui.theme.SecondaryGreen

@Composable
fun RegisterScreen() {
    AppScaffold {
        Register()
    }
}

@Composable
fun Register() {
    Column {
        Header(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(60.dp))
        FullNameField()
        Spacer(modifier = Modifier.padding(15.dp))
        EmailField()
        Spacer(modifier = Modifier.padding(15.dp))
        PasswordField()
        Spacer(modifier = Modifier.padding(15.dp))
        ConfirmPasswordField()
        Spacer(modifier = Modifier.padding(50.dp))
        RegisterButton(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(50.dp))
        AlreadyRegistered(Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun AlreadyRegistered(modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.register_already_have_account),
            style = MyTypography.labelSmall,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = stringResource(id = R.string.register_login),
            style = MyTypography.titleSmall,
            color = SecondaryGreen
        )
    }
}

@Composable
fun RegisterButton(modifier: Modifier) {
    PrimaryButton(
        modifier = modifier,
        buttonText = R.string.register,
        onClick = {},
        maxButtonWidth = 0.6f
    )
}

@Composable
fun ConfirmPasswordField() {
    InputField(
        value = "",
        placeholderId = R.string.register_confirm_password,
        keyboardType = KeyboardType.Password,
        onValueChange = {}
    )
}

@Composable
fun PasswordField() {
    InputField(
        value = "",
        placeholderId = R.string.register_create_password,
        keyboardType = KeyboardType.Password,
        onValueChange = {}
    )
}

@Composable
fun EmailField() {
    InputField(
        value = "",
        placeholderId = R.string.register_email,
        keyboardType = KeyboardType.Email,
        onValueChange = {}
    )
}

@Composable
fun FullNameField() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        InputField(
            value = "",
            placeholderId = R.string.register_first_name,
            keyboardType = KeyboardType.Text,
            onValueChange = {},
            maxWidth = 0.45f
        )
        InputField(
            value = "",
            placeholderId = R.string.register_last_name,
            keyboardType = KeyboardType.Text,
            onValueChange = {},
            maxWidth = 0.9f
        )
    }
}

@Composable
fun Header(modifier: Modifier) {
    LoginHeader(
        modifier = modifier,
        headerText = R.string.register
    )
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}