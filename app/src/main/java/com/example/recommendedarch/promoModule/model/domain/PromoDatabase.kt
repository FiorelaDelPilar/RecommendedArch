package com.example.recommendedarch.promoModule.model.domain

import com.example.recommendedarch.common.dataAccess.local.getAllPromos
import com.example.recommendedarch.common.entities.Promo

class PromoDatabase {
    fun getPromos(): List<Promo> {
        val result = getAllPromos()
        return result.ifEmpty {
            throw Exception()
        }
    }
}