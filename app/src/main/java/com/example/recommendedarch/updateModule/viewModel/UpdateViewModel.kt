package com.example.recommendedarch.updateModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.utils.Constants
import com.example.recommendedarch.updateModule.model.UpdateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.recommendedarch.R

class UpdateViewModel(private val repository: UpdateRepository) : ViewModel() {
    private val _inProgess = MutableLiveData<Boolean>()
    val inProgess: LiveData<Boolean> = _inProgess

    private val _snackbarMsg = MutableLiveData<Int>()
    val snackbarMsg: LiveData<Int> = _snackbarMsg

    private val _wine = MutableLiveData<Wine>()
    val wine: LiveData<Wine> = _wine

    fun requestWine(id: Double) {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                withContext(Dispatchers.IO) {
                    repository.requestWine(id) { wine ->
                        _wine.postValue(wine)
                    }
                }
            } catch (e: MyException) {
                _snackbarMsg.postValue(e.resMsg)
            } finally {
                _inProgess.value = Constants.HIDE
            }


        }
    }

    fun updateWine(newRating: String) {
        viewModelScope.launch {
            _inProgess.value = Constants.SHOW
            try {
                withContext(Dispatchers.IO) {
                    _wine.value?.let {
                        it.rating.average = newRating
                        repository.updateWine(it) {
                            _snackbarMsg.postValue(R.string.room_save_success)
                        }
                    }

                }
            } catch (e: MyException) {
                _snackbarMsg.postValue(e.resMsg)
            } finally {
                _inProgess.value = Constants.HIDE
            }
        }
    }
}