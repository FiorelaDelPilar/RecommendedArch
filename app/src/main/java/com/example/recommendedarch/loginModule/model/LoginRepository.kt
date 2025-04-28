package com.example.recommendedarch.loginModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.model.BaseRepository
import com.example.recommendedarch.common.utils.Constants
import com.example.recommendedarch.loginModule.model.domain.LoginAuth

class LoginRepository(private val auth: LoginAuth) : BaseRepository() {
    suspend fun checkAuth(callback: (Boolean) -> Unit) {
        executeAction(MyException(Constants.EC_AUTH, R.string.login_auth_fail)) {
            callback(auth.checkAuth())
        }
    }

    suspend fun login(username: String?, pin: String?, callback: (Boolean) -> Unit) {
        executeAction(MyException(Constants.EC_LOGIN, R.string.login_login_fail)) {
            callback(auth.login(username, pin))
        }
    }
}