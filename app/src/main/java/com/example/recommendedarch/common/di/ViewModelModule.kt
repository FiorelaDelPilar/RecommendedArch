package com.example.recommendedarch.common.di

import com.example.recommendedarch.favouriteModule.viewModel.FavouriteViewModel
import com.example.recommendedarch.homeModule.viewModel.HomeViewModel
import com.example.recommendedarch.updateModule.viewModel.UpdateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
    viewModel { UpdateViewModel(get()) }
}