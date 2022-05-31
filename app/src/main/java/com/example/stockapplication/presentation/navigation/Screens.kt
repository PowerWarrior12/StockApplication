package com.example.stockapplication.presentation.navigation

import com.example.stockapplication.presentation.stocksFragment.StocksFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun generateStocksFragment() = FragmentScreen{
        StocksFragment.newInstance()
    }
}