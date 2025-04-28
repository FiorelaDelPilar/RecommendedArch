package com.example.recommendedarch.homeModule.model.domain

import com.example.recommendedarch.common.dataAccess.retrofit.WineService

class HomeWineService(private val service: WineService) {
    suspend fun getRedWines() = service.getRedWines()
}