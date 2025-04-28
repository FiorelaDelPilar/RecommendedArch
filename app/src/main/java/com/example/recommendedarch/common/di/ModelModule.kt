package com.example.recommendedarch.common.di

import com.example.recommendedarch.favouriteModule.model.FavouriteRepository
import com.example.recommendedarch.homeModule.model.HomeRepository
import org.koin.dsl.module

val modelModule = module {
    factory { HomeRepository(get(), get()) }
    factory { FavouriteRepository(get()) }
}