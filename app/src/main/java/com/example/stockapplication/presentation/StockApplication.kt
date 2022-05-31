package com.example.stockapplication.presentation

import android.app.Application
import com.example.stockapplication.presentation.di.AppComponent
import com.example.stockapplication.presentation.di.DaggerAppComponent

const val APP_COMPONENT_ERROR = "The component is not initialized"

class StockApplication: Application() {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent get() = checkNotNull(_appComponent) { APP_COMPONENT_ERROR }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        _appComponent = DaggerAppComponent.create()
    }

    companion object {
        var INSTANCE: StockApplication? = null
    }
}