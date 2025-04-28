package com.example.recommendedarch.common.di

import android.app.Application
import androidx.room.Room
import com.example.recommendedarch.common.dataAccess.retrofit.WineService
import com.example.recommendedarch.common.dataAccess.room.WineDao
import com.example.recommendedarch.common.dataAccess.room.WineDatabase
import com.example.recommendedarch.common.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//ROOM
fun provideDatabase(application: Application): WineDatabase {
    return Room.databaseBuilder(
        application,
        WineDatabase::class.java,
        "WineDatabase"
    ).build()
}

fun provideDato(database: WineDatabase): WineDao = database.wineDao()

//RETROFIT
fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideWineService(retrofit: Retrofit): WineService = retrofit.create(WineService::class.java)

val dataSourceModule = module {
    //Room
    single { provideDatabase(get()) }
    single { provideDato(get()) }

    //Retrofit
    single { provideGsonConverterFactory() }
    single { provideRetrofit(get()) }
    single { provideWineService(get()) }
}