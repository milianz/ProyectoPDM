package com.hnrylvo.inmomarket.ux.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hnrylvo.inmomarket.data.networking.model.request.LoginRequest
import com.hnrylvo.inmomarket.data.repository.AuthRepository
import com.hnrylvo.inmomarket.data.utils.onFailure
import com.hnrylvo.inmomarket.data.utils.onSuccess
import com.hnrylvo.inmomarket.utils.isEmailValid
import com.hnrylvo.inmomarket.utils.isPasswordValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginEnabled = MutableStateFlow(false)
    val loginEnabled = _loginEnabled.asStateFlow()

    fun onValueChange(email : String, password : String){
        _email.update { email }
        _password.update { password }
        checkFormEnabled()
    }

    private fun checkFormEnabled() {
        val isFormEnabled = _email.value.isNotEmpty() &&
                _password.value.isNotEmpty() &&
                _email.value.isEmailValid() &&
                _password.value.isPasswordValid()
        _loginEnabled.value = isFormEnabled
    }

    fun login() {
        val request = LoginRequest(
            email = _email.value,
            password = _password.value
        )
        viewModelScope.launch {
            authRepository.login(request).collectLatest { response ->
                response.onSuccess {
                    Log.d("LoginViewModel", "login: ${it.email}")
                }
                response.onFailure {
                    Log.e("LoginViewModel", "login: ${it.throwable.message}")
                }
            }
        }
    }
}
