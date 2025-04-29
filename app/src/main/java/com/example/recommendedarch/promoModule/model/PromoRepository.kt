package com.example.recommendedarch.promoModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.entities.Promo
import com.example.recommendedarch.common.model.BaseRepository
import com.example.recommendedarch.common.utils.Constants
import com.example.recommendedarch.promoModule.model.domain.PromoDatabase

class PromoRepository(private val db: PromoDatabase) : BaseRepository() {
    suspend fun getPromos(callback: (List<Promo>) -> Unit) {
        executeAction(MyException(Constants.EC_REQUEST, R.string.promo_request_fail)) {
            callback(db.getPromos())
        }
    }
}