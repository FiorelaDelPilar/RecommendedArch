package com.example.recommendedarch.accountModule.model

import com.example.recommendedarch.common.dataAccess.local.FakeFirebaseAuth
import com.example.recommendedarch.common.entities.FirebaseUser

class AccountRepository(private val auth: FakeFirebaseAuth) {
    suspend fun signOut(): Boolean {
        return auth.signOut()
    }

    suspend fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }
}