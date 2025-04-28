package com.example.recommendedarch.homeModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import kotlin.random.Random
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.model.BaseRepository
import com.example.recommendedarch.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val db: RoomDatabase, private val service: WineService) :
    BaseRepository() {

    suspend fun getAllWines(callback: (List<Wine>) -> Unit) = withContext(Dispatchers.IO) {
        executeAction(MyException(Constants.EC_REQUEST, R.string.common_general_fail)) {
            val serverOk = if (Random.nextBoolean()) true else Random.nextBoolean()
            val wines = if (serverOk) service.getRedWines() else listOf()
            callback(wines)
        }
    }

    suspend fun addWine(wine: Wine, callback: () -> Unit) = withContext(Dispatchers.IO) {
        executeAction {
            val result = db.addWine(wine)
            if (result == -1L) {
                throw MyException(Constants.EC_SAVE_WINE, R.string.room_save_fail)
            } else {
                callback()
            }
        }

    }
}