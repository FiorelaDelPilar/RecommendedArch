package com.example.recommendedarch.loginModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recommendedarch.common.viewModel.BaseViewModel
import com.example.recommendedarch.loginModule.model.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : BaseViewModel() {
    val username = MutableLiveData("cursoant")
    val pin = MutableLiveData("1234")

    private val _isAuthValid = MutableLiveData<Boolean>()
    val isAuthValid: LiveData<Boolean> = _isAuthValid

    init {
        checkAuth()
    }

    private fun checkAuth() {
        executeAction {
            repository.checkAuth { result ->
                _isAuthValid.value = result
            }
        }
    }

    fun login() {
        executeAction {
            repository.login(username.value, pin.value) { result ->
                _isAuthValid.value = result
            }
        }
    }

    override fun onPause() = clearValues()
}