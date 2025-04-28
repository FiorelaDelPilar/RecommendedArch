package com.example.recommendedarch.updateModule.model.domain

import com.example.recommendedarch.common.dataAccess.room.WineDao
import com.example.recommendedarch.common.entities.Wine

class UpdateRoomDatabase(private val dao: WineDao) {

    fun getWineById(id: Double) = dao.getWineById(id)

    fun updateWined(wine: Wine?, newRating: String, callback: () -> Unit) {
        wine?.let {
            wine.rating.average = newRating
            val result = dao.updateWine(wine)
            if (result == 0) throw Exception() else callback()
        }
    }
}