package com.example.recommendedarch.homeModule.model

import com.example.recommendedarch.common.dataAccess.room.WineDao
import com.example.recommendedarch.common.entities.Wine

class HomeRoomDatabase(private val dao: WineDao) {
    //private val dao: WineDao by lazy { WineApplication.database.wineDao() }

    fun addWine(wine: Wine) = dao.addWine(wine)
}