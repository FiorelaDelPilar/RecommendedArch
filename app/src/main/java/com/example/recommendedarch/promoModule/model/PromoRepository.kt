package com.example.recommendedarch.promoModule.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.entities.Promo
import com.example.recommendedarch.common.utils.Constants

class PromoRepository(private val db: Database) {
    fun getPromos(): List<Promo> {
        val result = db.getPromos()
        return result.ifEmpty {
            throw MyException(Constants.EC_REQUEST, R.string.promo_request_fail)
        }
    }
}