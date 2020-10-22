package com.kasko.victor.testapp.toddoapp

import android.app.Application
import com.kasko.victor.testapp.toddoapp.di.apiModule
import com.kasko.victor.testapp.toddoapp.di.appModule
import com.kasko.victor.testapp.toddoapp.di.roomDBmodule
import com.kasko.victor.testapp.toddoapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application(){
    override fun onCreate() {
        super.onCreate()
        setUpCoin()
    }

    private fun setUpCoin() {
        startKoin {
            androidContext(this@App)
            modules(
                roomDBmodule,
                viewModelModule,
                appModule,
                apiModule
            )
        }
    }
}