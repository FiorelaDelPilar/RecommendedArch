package com.example.recommendedarch.updateModule.model

import com.example.recommendedarch.WineApplication
import com.example.recommendedarch.common.dataAccess.room.WineDao
import com.example.recommendedarch.common.entities.Wine

class RoomDatabase {
    private val dao: WineDao by lazy { WineApplication.database.wineDao() }

    fun getWineById(id: Double) = dao.getWineById(id)
    fun updateWined(wine: Wine) = dao.updateWine(wine)
}