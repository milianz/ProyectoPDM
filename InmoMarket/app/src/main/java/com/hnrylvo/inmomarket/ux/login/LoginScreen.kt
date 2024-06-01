package com.hnrylvo.inmomarket.ux.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.compose.buttons.PrimaryButton
import com.hnrylvo.inmomarket.ui.compose.containers.AppScaffold
import com.hnrylvo.inmomarket.ui.compose.dividers.HorizontalDivider
import com.hnrylvo.inmomarket.ui.compose.headers.LoginHeader
import com.hnrylvo.inmomarket.ui.compose.inputs.InputField
import com.hnrylvo.inmomarket.ui.theme.MyTypography
import com.hnrylvo.inmomarket.ui.theme.PrimaryGreen
import com.hnrylvo.inmomarket.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.ui.theme.White

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    AppScaffold {
        Login(viewModel)
    }
}

@Composable
fun Login(viewModel: LoginViewModel) {
    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    Column {
        Header(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(60.dp))
        EmailTextField(email) { viewModel.onValueChange(it, password) }
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordTextField(password) { viewModel.onValueChange(email, it) }
        Spacer(modifier = Modifier.padding(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.padding(45.dp))
        LoginButton(onClick = { viewModel.login() })
        Spacer(modifier = Modifier.padding(16.dp))
        OrSection()
        Spacer(modifier = Modifier.padding(16.dp))
        GoogleButton()
        Spacer(modifier = Modifier.padding(16.dp))
        DontHaveAccount(Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun DontHaveAccount(modifier: Modifier) {
    Row(modifier = modifier){
        Text(
            text = stringResource(id = R.string.login_dont_have_account),
            style = MyTypography.labelSmall,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = stringResource(id = R.string.login_create_account),
            style = MyTypography.titleSmall,
            color = PrimaryGreen
        )
    }
}

@Composable
fun GoogleButton() {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = White),
        shape = RoundedCornerShape(16.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = stringResource(id = R.string.login_google_icon)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(id = R.string.login_google),
            style = MyTypography.titleMedium,
            color = PrimaryGreen
        )
    }
}

@Composable
fun OrSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(maxWidth = 0.4f)
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.login_or),
            style = MyTypography.labelLarge,
            color = SecondaryGreen
        )
        HorizontalDivider(maxWidth = 0.8f)
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    PrimaryButton(
        buttonText = R.string.login_login,
        onClick = onClick
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.login_forgot_password),
        style = MyTypography.titleSmall,
        color = SecondaryGreen
    )
}

@Composable
fun PasswordTextField(password: String, onValueChange: (String) -> Unit) {
    InputField(
        value = password,
        placeholderId = R.string.login_password,
        keyboardType = KeyboardType.Password,
        onValueChange = {onValueChange(it)}
    )
}

@Composable
fun EmailTextField(email: String, onValueChange: (String) -> Unit) {
    InputField(
        value = email,
        placeholderId = R.string.login_email,
        keyboardType = KeyboardType.Email,
        onValueChange = {onValueChange(it)}
    )
}

@Composable
fun Header(modifier: Modifier) {
    LoginHeader(
        modifier = modifier,
        headerText = R.string.login_header
    )
}

@Composable
@Preview(showSystemUi = true)
fun LoginScreenPreview() {
    LoginScreen(viewModel = LoginViewModel())
}
