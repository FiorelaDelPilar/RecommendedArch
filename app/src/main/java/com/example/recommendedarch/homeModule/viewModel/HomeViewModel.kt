package com.example.recommendedarch.homeModule.viewModel

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.viewModel.BaseWineViewModel
import com.example.recommendedarch.homeModule.model.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : BaseWineViewModel() {
    init {
        getAllWines()
    }

    override fun onPause() = clearValue()

    override fun getAllWines() {
        executeAction {
            repository.getAllWines { wines ->
                setWines(wines)
            }
        }
    }

    override fun addWine(wine: Wine) {
        executeAction {
            repository.addWine(wine) {
                setSnackbarMsg(R.string.room_save_success)

            }
        }
    }
}