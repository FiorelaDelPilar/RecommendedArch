package com.example.recommendedarch.promoModule.model

import com.example.recommendedarch.common.dataAccess.local.getAllPromos

class Database {
    fun getPromos() = getAllPromos()
}