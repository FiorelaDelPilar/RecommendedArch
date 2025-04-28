package com.example.recommendedarch.homeModule.model

import com.example.recommendedarch.common.dataAccess.retrofit.WineService

class HomeWineService(private val service: WineService) {
    suspend fun getRedWines() = service.getRedWines()

  /*
    private fun getService(): WineService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WineService::class.java)
    }
   */
}