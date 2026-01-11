package com.example.booklistcomposedemo

import android.app.Application
import com.example.booklistcomposedemo.di.mainModule
import com.example.booklistcomposedemo.di.useCaseModule
import com.example.booklistcomposedemo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            allowOverride(false)  // Strict mode
            modules(listOf(mainModule, useCaseModule, viewModelModule))
        }
    }
}
