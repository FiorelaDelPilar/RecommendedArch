package com.example.recommendedarch.favouriteModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.model.BaseRepository
import com.example.recommendedarch.common.utils.Constants
import com.example.recommendedarch.favouriteModule.model.domain.FavouriteRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepository(private val db: FavouriteRoomDatabase) : BaseRepository() {
    suspend fun getAllWines(callback: (List<Wine>) -> Unit) = withContext(Dispatchers.IO) {
        executeAction(MyException(Constants.EC_REQUEST, R.string.home_no_wines)) {
            callback(db.getAllWines())
        }
    }

    suspend fun updateFavourite(wine: Wine, callback: (Int) -> Unit) = withContext(Dispatchers.IO) {
        executeAction(MyException(Constants.EC_UPDATE_WINE, R.string.room_update_fail)) {
            db.updateFavourite(wine) { result ->
                callback(result)
            }
        }
    }
}