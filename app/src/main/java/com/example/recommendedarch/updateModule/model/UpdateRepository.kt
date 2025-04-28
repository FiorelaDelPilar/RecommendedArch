package com.example.recommendedarch.updateModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.model.BaseRepository
import com.example.recommendedarch.common.utils.Constants
import com.example.recommendedarch.updateModule.model.domain.UpdateRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateRepository(private val db: UpdateRoomDatabase) : BaseRepository() {
    suspend fun requestWine(id: Double, callback: (Wine) -> Unit) = withContext(Dispatchers.IO) {
        executeAction(MyException(Constants.EC_GET_WINE, R.string.room_request_fail)) {
            callback(db.getWineById(id))
        }
    }

    suspend fun updateWine(wine: Wine?, newRating: String, callback: () -> Unit) =
        withContext(Dispatchers.IO) {
            executeAction(MyException(Constants.EC_UPDATE_WINE, R.string.room_update_fail)) {
                db.updateWined(wine, newRating) { callback() }
            }
        }
}