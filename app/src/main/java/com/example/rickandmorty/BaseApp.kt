package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.appModule
import com.example.rickandmorty.di.dataModule
import com.example.rickandmorty.di.domainModule
import com.example.rickandmorty.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApp)
            modules(listOf(networkModule, appModule, dataModule, domainModule))
        }
    }
}