package com.example.recommendedarch.common.di

import com.example.recommendedarch.homeModule.model.HomeRoomDatabase
import com.example.recommendedarch.homeModule.model.HomeWineService
import org.koin.dsl.module

val domainModule = module {
    factory { HomeRoomDatabase(get()) }
    factory { HomeWineService(get()) }
}