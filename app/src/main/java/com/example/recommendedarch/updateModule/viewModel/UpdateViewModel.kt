package com.example.recommendedarch.updateModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.updateModule.model.UpdateRepository
import com.example.recommendedarch.R
import com.example.recommendedarch.common.viewModel.BaseViewModel

class UpdateViewModel(private val repository: UpdateRepository) : BaseViewModel() {
    private val _wine = MutableLiveData<Wine>()
    val wine: LiveData<Wine> = _wine

    fun requestWine(id: Double) {
        executeAction {
            repository.requestWine(id) { wine ->
                _wine.postValue(wine)
            }
        }
    }

    fun updateWine(newRating: String) {
        executeAction {
            repository.updateWine(_wine.value, newRating) {
                setSnackbarMsg(R.string.room_save_success)
            }
        }
    }

    override fun onPause() = clearValues()
}