package com.example.recommendedarch.common.di

import com.example.recommendedarch.favouriteModule.model.FavouriteRepository
import com.example.recommendedarch.homeModule.model.HomeRepository
import com.example.recommendedarch.loginModule.model.LoginRepository
import com.example.recommendedarch.updateModule.model.UpdateRepository
import org.koin.dsl.module

val modelModule = module {
    factory { HomeRepository(get(), get()) }
    factory { FavouriteRepository(get()) }
    factory { UpdateRepository(get()) }
    factory { LoginRepository(get()) }
}