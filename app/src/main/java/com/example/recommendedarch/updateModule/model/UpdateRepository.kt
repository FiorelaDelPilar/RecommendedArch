package com.example.recommendedarch.updateModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.utils.Constants

class UpdateRepository(private val db: RoomDatabase) {
    fun requestWine(id: Double, callback: (Wine) -> Unit) {
        try {
            val result = db.getWineById(id)
            callback(result)
        } catch (e: MyException) {
            throw MyException(Constants.EC_GET_WINE, R.string.room_request_fail)
        }
    }

    fun updateWine(wine: Wine, callback: () -> Unit) {
        val result = db.updateWined(wine)

        if (result == 0) {
            throw MyException(Constants.EC_UPDATE_WINE, R.string.room_update_fail)
        } else {
            callback()
        }
    }
}