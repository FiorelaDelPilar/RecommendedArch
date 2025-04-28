package com.example.recommendedarch.common.di

import com.example.recommendedarch.common.dataAccess.local.FakeFirebaseAuth
import com.example.recommendedarch.favouriteModule.model.domain.FavouriteRoomDatabase
import com.example.recommendedarch.homeModule.model.domain.HomeRoomDatabase
import com.example.recommendedarch.homeModule.model.domain.HomeWineService
import com.example.recommendedarch.loginModule.model.domain.LoginAuth
import com.example.recommendedarch.updateModule.model.domain.UpdateRoomDatabase
import org.koin.dsl.module

val domainModule = module {
    factory { HomeRoomDatabase(get()) }
    factory { HomeWineService(get()) }
    factory { FavouriteRoomDatabase(get()) }
    factory { UpdateRoomDatabase(get()) }
    single { FakeFirebaseAuth() }
    factory { LoginAuth(get()) }
}