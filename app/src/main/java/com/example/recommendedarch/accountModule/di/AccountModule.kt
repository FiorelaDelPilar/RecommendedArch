package com.example.recommendedarch.accountModule.di

import com.example.recommendedarch.accountModule.model.AccountRepository
import com.example.recommendedarch.accountModule.model.domain.AccountAuth
import com.example.recommendedarch.accountModule.viewModel.AccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {
    single { AccountAuth(get()) }
    factory { AccountRepository(get()) }
    viewModel { AccountViewModel(get()) }
}