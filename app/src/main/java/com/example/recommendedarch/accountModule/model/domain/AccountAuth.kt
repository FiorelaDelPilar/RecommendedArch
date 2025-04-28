package com.example.recommendedarch.accountModule.model.domain

import com.example.recommendedarch.common.dataAccess.local.FakeFirebaseAuth
import com.example.recommendedarch.common.entities.FirebaseUser

class AccountAuth(private val auth: FakeFirebaseAuth) {
    suspend fun signOut(): Boolean = auth.signOut()

    suspend fun getCurrentUser(): FirebaseUser? = auth.getCurrentUser()
}