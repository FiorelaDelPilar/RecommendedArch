package com.example.recommendedarch.accountModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recommendedarch.accountModule.model.AccountRepository
import com.example.recommendedarch.common.entities.FirebaseUser
import com.example.recommendedarch.common.viewModel.BaseViewModel

class AccountViewModel(private val repository: AccountRepository) : BaseViewModel() {
    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    private val _isSignOut = MutableLiveData<Boolean>()
    val isSignOut: LiveData<Boolean> = _isSignOut

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        executeAction {
            repository.getCurrentUser { result ->
                _user.value = result
            }
        }
    }

    fun singOut() {
        executeAction {
            repository.signOut { result ->
                _isSignOut.value = result
            }
        }
    }

    override fun onPause() = clearValues()
}