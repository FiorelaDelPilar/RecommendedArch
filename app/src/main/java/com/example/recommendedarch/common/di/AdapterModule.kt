package com.example.recommendedarch.common.di

import com.example.recommendedarch.common.utils.OnClickListener
import com.example.recommendedarch.favouriteModule.view.WineFavListAdapter
import com.example.recommendedarch.homeModule.view.WineDiff
import com.example.recommendedarch.homeModule.view.WineListAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { WineDiff() }
    factory<WineListAdapter> { (listener: OnClickListener) -> WineListAdapter(listener, get()) }
    factory<WineFavListAdapter> { (listener: OnClickListener) -> WineFavListAdapter(listener, get()) }
}