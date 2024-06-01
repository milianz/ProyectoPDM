package com.hnrylvo.inmomarket.ux.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hnrylvo.inmomarket.data.networking.model.request.LoginRequest
import com.hnrylvo.inmomarket.data.repository.AuthRepository
import com.hnrylvo.inmomarket.data.utils.onFailure
import com.hnrylvo.inmomarket.data.utils.onSuccess
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    fun onValueChange(email : String, password : String){
        _email.value = email
        _password.value = password
    }

    fun login() {
        val request = LoginRequest(
            email = _email.value.orEmpty(),
            password = _password.value.orEmpty()
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
