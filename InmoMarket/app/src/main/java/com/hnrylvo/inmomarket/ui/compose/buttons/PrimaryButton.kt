package com.hnrylvo.inmomarket.ui.compose.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.theme.BackgroundGreen
import com.hnrylvo.inmomarket.ui.theme.MyTypography
import com.hnrylvo.inmomarket.ui.theme.PrimaryGreen

@Composable
fun PrimaryButton(
    onClick: () -> Unit = {},
    enabled: Boolean = false,
    buttonText: Int,
    maxButtonWidth: Float = 1f,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(maxButtonWidth)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryGreen
        ),
        shape = RoundedCornerShape(16.dp),
        enabled = enabled
    ) {
        Text(
            text = stringResource(id = buttonText),
            style = MyTypography.titleMedium,
            color = BackgroundGreen
        )
    }
}

@Composable
@Preview
fun PreviewPrimaryButton() {
    PrimaryButton(onClick = { /*TODO*/ }, buttonText = R.string.login_login)
}
