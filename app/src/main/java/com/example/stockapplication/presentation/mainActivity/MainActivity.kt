package com.example.stockapplication.presentation.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.stockapplication.R
import com.example.stockapplication.presentation.StockApplication
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var mainViewModelFactory: MainViewModel.MainViewModelFactory

    private val viewModel by viewModels<MainViewModel> { mainViewModelFactory }

    private val navigator: Navigator = object : AppNavigator(this, R.id.container, supportFragmentManager) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StockApplication.INSTANCE?.appComponent?.inject(this)
        viewModel
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }
}