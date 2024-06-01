package com.hnrylvo.inmomarket.ui.compose.containers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hnrylvo.inmomarket.ui.theme.BackgroundGreen

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        containerColor = BackgroundGreen
    ) { paddingValues ->
        Box(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            content()
        }
    }
}
