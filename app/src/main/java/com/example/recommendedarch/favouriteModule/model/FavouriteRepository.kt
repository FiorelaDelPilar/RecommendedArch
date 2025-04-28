package com.example.recommendedarch.favouriteModule.model

import com.example.recommendedarch.common.entities.Wine

class FavouriteRepository(private val db: RoomDatabase) {
    fun getAllWines(): List<Wine>? {
        return try {
            val result = db.getAllWines()
            result
        } catch (e: Exception) {
            null
        }

    }

    fun addWine(wine: Wine): Long {
        return db.addWine(wine)
    }

    fun deleteWine(wine: Wine): Int {
        return db.deleteWine(wine)
    }
}