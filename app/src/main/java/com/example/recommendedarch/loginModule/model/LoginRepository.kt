package com.example.recommendedarch.loginModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.dataAccess.local.FakeFirebaseAuth
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.utils.Constants

class LoginRepository(private val auth: FakeFirebaseAuth) {
    suspend fun checkAuth(): Boolean {
        return auth.isValidAuth()
    }

    suspend fun login(username: String, pin: String): Boolean {
        val result = auth.login(username, pin)
        if (!result) throw MyException(Constants.EC_LOGIN, R.string.login_login_fail)
        return true
    }
}