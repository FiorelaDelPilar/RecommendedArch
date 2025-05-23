package com.example.recommendedarch

import android.app.Application
import com.example.recommendedarch.accountModule.di.accountModule
import com.example.recommendedarch.common.di.adapterModule
import com.example.recommendedarch.common.di.dataSourceModule
import com.example.recommendedarch.common.di.domainModule
import com.example.recommendedarch.common.di.modelModule
import com.example.recommendedarch.common.di.viewModelModule
import com.example.recommendedarch.promoModule.di.promoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/****
 * Project: Wines
 * From: com.cursosant.wines
 * Created by Alain Nicolás Tello on 06/02/24 at 20:23
 * All rights reserved 2024.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class WineApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WineApplication)
            modules(
                adapterModule,
                viewModelModule,
                modelModule,
                domainModule,
                dataSourceModule,
                accountModule,
                promoModule
            )
        }
    }
}