package com.example.stockapplication.presentation.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stockapplication.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel(private val router: Router): ViewModel() {

    init {
        openRootScreen()
    }

    private fun openRootScreen() {
        router.newRootScreen(Screens.generateStocksFragment())
    }

    class MainViewModelFactory @Inject constructor(private val router: Router): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(router) as T
        }
    }
}