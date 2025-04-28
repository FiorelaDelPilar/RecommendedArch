package com.example.recommendedarch.common.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {
    val showNavView = MutableLiveData<Boolean>()
}