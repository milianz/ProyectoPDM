package com.hnrylvo.inmomarket.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hnrylvo.inmomarket.R

// Set of Material typography styles to start with
private val IstokWebFamily = FontFamily(
    Font(R.font.istokweb_regular, FontWeight.Normal),
    Font(R.font.istokweb_bold, FontWeight.Bold)
)

val MyTypography = Typography(
    titleSmall = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    titleMedium = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    titleLarge = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = IstokWebFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
)
