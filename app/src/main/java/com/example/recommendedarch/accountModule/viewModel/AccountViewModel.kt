package com.example.recommendedarch.accountModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendedarch.R
import com.example.recommendedarch.accountModule.model.AccountRepository
import com.example.recommendedarch.common.entities.FirebaseUser
import com.example.recommendedarch.common.utils.Constants
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: AccountRepository) : ViewModel() {
    private val _inProgess = MutableLiveData<Boolean>()
    val inProgess: LiveData<Boolean> = _inProgess

    private val _snackbarMsg = MutableLiveData<Int>()
    val snackbarMsg: LiveData<Int> = _snackbarMsg

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    private val _isSignOut = MutableLiveData<Boolean>()
    val isSignOut: LiveData<Boolean> = _isSignOut

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                _user.value = repository.getCurrentUser()
            } catch (e: Exception) {
                _snackbarMsg.value = R.string.account_sign_out_fail
            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }

    fun singOut() {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                _isSignOut.value = repository.signOut()
            } catch (e: Exception) {
                _snackbarMsg.value = R.string.account_request_user_fail
            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }
}