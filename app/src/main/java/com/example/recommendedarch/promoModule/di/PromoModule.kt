package com.example.recommendedarch.promoModule.di

import com.example.recommendedarch.promoModule.model.PromoRepository
import com.example.recommendedarch.promoModule.model.domain.PromoDatabase
import com.example.recommendedarch.promoModule.view.adapters.PromoListAdapter
import com.example.recommendedarch.promoModule.view.adapters.PromoDiff
import com.example.recommendedarch.promoModule.viewModel.PromoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val promoModule = module {
    factory { PromoDatabase() }
    factory { PromoRepository(get()) }
    factory { PromoDiff() }
    factory { PromoListAdapter(get()) }
    viewModel { PromoViewModel(get()) }
}