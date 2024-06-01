package com.hnrylvo.inmomarket.ui.compose.dividers

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider(maxWidth: Float = 1f) {
    Row {
        Divider(
            modifier = Modifier
                .fillMaxWidth(maxWidth)
                .height(2.dp),
        )
    }
}

@Composable
@Preview
fun HorizontalDividerPreview() {
    HorizontalDivider(maxWidth = .45f)
}
