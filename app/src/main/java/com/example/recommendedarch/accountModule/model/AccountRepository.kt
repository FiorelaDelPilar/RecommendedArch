package com.example.recommendedarch.accountModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.accountModule.model.domain.AccountAuth
import com.example.recommendedarch.common.entities.FirebaseUser
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.model.BaseRepository
import com.example.recommendedarch.common.utils.Constants

class AccountRepository(private val auth: AccountAuth) : BaseRepository() {
    suspend fun signOut(callback: (Boolean) -> Unit) {
        executeAction(MyException(Constants.EC_AUTH, R.string.login_auth_fail)) {
            callback(auth.signOut())
        }
    }

    suspend fun getCurrentUser(callback: (FirebaseUser?) -> Unit) {
        executeAction(MyException(Constants.EC_AUTH, R.string.login_auth_fail)) {
            callback(auth.getCurrentUser())
        }
    }
}