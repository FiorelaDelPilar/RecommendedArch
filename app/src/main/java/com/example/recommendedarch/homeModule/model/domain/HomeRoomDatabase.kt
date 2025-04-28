package com.example.recommendedarch.homeModule.model.domain

import com.example.recommendedarch.common.dataAccess.room.WineDao
import com.example.recommendedarch.common.entities.Wine

class HomeRoomDatabase(private val dao: WineDao) {
    fun addWine(wine: Wine) = dao.addWine(wine)
}