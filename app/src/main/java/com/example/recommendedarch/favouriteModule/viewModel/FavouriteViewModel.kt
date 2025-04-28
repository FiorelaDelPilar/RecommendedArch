package com.example.recommendedarch.favouriteModule.viewModel

import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.viewModel.BaseWineViewModel
import com.example.recommendedarch.favouriteModule.model.FavouriteRepository

class FavouriteViewModel(private val repository: FavouriteRepository) : BaseWineViewModel() {
    init {
        getAllWines()
    }

    override fun getAllWines() {
        executeAction {
            repository.getAllWines { wines ->
                setWines(wines)
            }
        }
    }

    fun updateFavourite(wine: Wine) {
        executeAction {
            repository.updateFavourite(wine) { resMsg ->
                setSnackbarMsg(resMsg)
            }
        }
    }

    override fun onPause() = clearValues()
}