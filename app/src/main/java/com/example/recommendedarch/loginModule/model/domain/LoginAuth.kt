package com.example.recommendedarch.loginModule.model.domain

import com.example.recommendedarch.common.dataAccess.local.FakeFirebaseAuth

class LoginAuth(private val auth: FakeFirebaseAuth) {
    suspend fun checkAuth(): Boolean = auth.isValidAuth()

    suspend fun login(username: String?, pin: String?): Boolean {
        if (!username.isNullOrEmpty() && !pin.isNullOrEmpty())
            return auth.login(username, pin)
        return false
    }
}