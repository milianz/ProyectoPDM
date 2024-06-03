package com.hnrylvo.inmomarket.ux.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.compose.buttons.PrimaryButton
import com.hnrylvo.inmomarket.ui.compose.containers.AppScaffold
import com.hnrylvo.inmomarket.ui.compose.headers.LoginHeader
import com.hnrylvo.inmomarket.ui.compose.inputs.InputField
import com.hnrylvo.inmomarket.ui.theme.MyTypography
import com.hnrylvo.inmomarket.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.ux.login.LoginRoute

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel = RegisterViewModel()
    AppScaffold {
        Register(viewModel, navController)
    }
}

@Composable
fun Register(viewModel: RegisterViewModel, navController: NavController) {
    val name by viewModel.name.collectAsState()
    val lastName by viewModel.lastname.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val registerButtonEnabled by viewModel.enabled.collectAsState()

    Column {
        Header(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(60.dp))
        FullNameField(
            name = name,
            onNameChange = {viewModel.onValueChanged(FieldType.NAME, it)},
            lastname = lastName,
            onLastNameChange = {viewModel.onValueChanged(FieldType.LASTNAME, it)}
        )
        Spacer(modifier = Modifier.padding(15.dp))
        EmailField(
            email = email,
            onEmailChange = {viewModel.onValueChanged(FieldType.EMAIL, it)}
        )
        Spacer(modifier = Modifier.padding(15.dp))
        PasswordField(
            password = password,
            onPasswordChange = {viewModel.onValueChanged(FieldType.PASSWORD, it)}
        )
        Spacer(modifier = Modifier.padding(15.dp))
        ConfirmPasswordField(
            confirmPass = confirmPassword,
            onConfirmPassChange = {viewModel.onValueChanged(FieldType.CONFIRM_PASSWORD, it)}
        )
        Spacer(modifier = Modifier.padding(50.dp))
        RegisterButton(Modifier.align(
            Alignment.CenterHorizontally),
            enabled = registerButtonEnabled,
            navController
        )
        Spacer(modifier = Modifier.padding(50.dp))
        AlreadyRegistered(Modifier.align(Alignment.CenterHorizontally), navController)
    }
}

@Composable
fun AlreadyRegistered(modifier: Modifier, navController: NavController) {
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
            modifier = Modifier.clickable {
                navController.navigate(route = LoginRoute.route)
            },
            text = stringResource(id = R.string.register_login),
            style = MyTypography.titleSmall,
            color = SecondaryGreen
        )
    }
}

@Composable
fun RegisterButton(modifier: Modifier, enabled: Boolean, navController: NavController) {
    PrimaryButton(
        modifier = modifier,
        buttonText = R.string.register,
        onClick = {
            navController.navigate(route = LoginRoute.route)
        },
        maxButtonWidth = 0.6f,
        enabled = enabled
    )
}

@Composable
fun ConfirmPasswordField(
    confirmPass: String,
    onConfirmPassChange: (String) -> Unit
) {
    InputField(
        value = confirmPass,
        placeholderId = R.string.register_confirm_password,
        keyboardType = KeyboardType.Password,
        onValueChange = {onConfirmPassChange(it)}
    )
}

@Composable
fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    InputField(
        value = password,
        placeholderId = R.string.register_create_password,
        keyboardType = KeyboardType.Password,
        onValueChange = {onPasswordChange(it)}
    )
}

@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    InputField(
        value = email,
        placeholderId = R.string.register_email,
        keyboardType = KeyboardType.Email,
        onValueChange = {onEmailChange(it)}
    )
}

@Composable
fun FullNameField(
    name: String, onNameChange: (String) -> Unit,
    lastname: String, onLastNameChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        InputField(
            value = name,
            placeholderId = R.string.register_first_name,
            keyboardType = KeyboardType.Text,
            onValueChange = {onNameChange(it)},
            maxWidth = 0.45f
        )
        InputField(
            value = lastname,
            placeholderId = R.string.register_last_name,
            keyboardType = KeyboardType.Text,
            onValueChange = {onLastNameChange(it)},
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
    RegisterScreen(navController = NavController(LocalContext.current))
}