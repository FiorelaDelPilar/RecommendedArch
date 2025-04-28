package com.example.recommendedarch.homeModule.viewModel

import androidx.lifecycle.viewModelScope
import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.utils.Constants
import com.example.recommendedarch.common.viewModel.BaseWineViewModel
import com.example.recommendedarch.homeModule.model.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: HomeRepository) : BaseWineViewModel() {
    init {
        getAllWines()
    }

    override fun getAllWines() {
        viewModelScope.launch {
            setInProgress(Constants.SHOW)
            try {
                withContext(Dispatchers.IO) {
                    repository.getAllWines { wines ->
                        setWines(wines)
                    }
                }
            } catch (e: MyException) {
                setSnackbarMsg(e.resMsg)
            } finally {
                setInProgress(Constants.HIDE)
            }
        }
    }

    override fun addWine(wine: Wine) {
        viewModelScope.launch {
            setInProgress(Constants.SHOW)
            try {
                withContext(Dispatchers.IO) {
                    repository.addWine(wine) {
                        setSnackbarMsg(R.string.room_save_success)
                    }
                }
            } catch (e: MyException) {
                setSnackbarMsg(e.resMsg)
            } finally {
                setInProgress(Constants.HIDE)
            }
        }
    }
}