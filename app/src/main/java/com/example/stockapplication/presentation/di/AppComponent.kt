package com.example.stockapplication.presentation.di

import com.example.stockapplication.presentation.mainActivity.MainActivity
import com.example.stockapplication.presentation.stocksFragment.StocksFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [MainModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: StocksFragment)
}