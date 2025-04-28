package com.example.recommendedarch.homeModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import kotlin.random.Random
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.utils.Constants

class HomeRepository(private val db: RoomDatabase, private val service: WineService) {

    suspend fun getAllWines(callback: (List<Wine>) -> Unit) {
        return try {
            val serverOk = if (Random.nextBoolean()) true else Random.nextBoolean()
            val wines = if (serverOk) service.getRedWines() else listOf()
            callback(wines)
        } catch (e: Exception) {
            throw MyException(Constants.EC_REQUEST, R.string.common_general_fail)
        }
    }

    fun addWine(wine: Wine, callback: () -> Unit) {
        val result = db.addWine(wine)
        return if (result == -1L) {
            throw MyException(Constants.EC_SAVE_WINE, R.string.room_save_fail)
        } else {
            callback()
        }
    }
}