package com.hnrylvo.inmomarket.utils

import android.util.Patterns
import java.util.regex.Pattern

fun String.isPasswordValid(): Boolean {
    if (this.isEmpty()) return false
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"
    val pattern = Pattern.compile(passwordPattern)
    val matcher = pattern.matcher(this)

    return matcher.matches()
}

fun String.isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()