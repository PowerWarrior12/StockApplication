package com.example.stockapplication.presentation.stocksFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.domain.interactors.LoadCompanyStockInteractor
import com.example.stockapplication.domain.interactors.LoadCompanyStocksInteractor
import com.example.stockapplication.utils.Result
import com.example.stockapplication.utils.tickerFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.time.Duration
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class StocksViewModel(
    private val loadStocksInteractor: LoadCompanyStocksInteractor,
    private val loadCompanyStockInteractor: LoadCompanyStockInteractor
) : ViewModel() {

    val companyStocksLiveData = MutableLiveData<MutableList<CompanyStock>>()
    val loadLiveData = MutableLiveData<Boolean>(false)
    val errorLiveData = MutableLiveData<Boolean>(false)
    private var initialized = false
    private var visibleStocks = mutableListOf<CompanyStock>()

    init {
        viewModelScope.launch {
            loadLiveData.postValue(true)
            val companyStocksResult = loadStocksInteractor.run()
            if (companyStocksResult is Result.Success) {
                val stocks = companyStocksResult.data
                companyStocksLiveData.postValue(stocks.toMutableList())
            } else {
                errorLiveData.postValue(true)
            }
            loadLiveData.postValue(false)
        }
    }

    fun stockWasBound(stock: CompanyStock) {
        viewModelScope.launch {
            val stocksResult = updateCompanyStock(companyStocksLiveData.value?.toMutableList()!!, stock)
            if (stocksResult is Result.Success) {
                companyStocksLiveData.postValue(stocksResult.data!!)
            } else {
                errorLiveData.postValue(true)
            }
        }
    }

    fun visibleStockItemsChanged(startStockIndex: Int, endStockIndex: Int) {
        visibleStocks = companyStocksLiveData.value!!.subList(startStockIndex, endStockIndex)
        if (!initialized) {
            initialized()
        }
    }

    private fun initialized() {
        initialized = true
        tickerFlow(20.seconds).onEach {
            updateVisibleStocks()
        }.launchIn(viewModelScope)
    }

    private suspend fun updateVisibleStocks() {
        Log.d("UpdateStocks", "Update")
        var currentStocks = companyStocksLiveData.value?.toMutableList()!!
        visibleStocks.forEach { stock ->
            val stocksResult = updateCompanyStock(currentStocks, stock)
            if (stocksResult is Result.Success) {
                currentStocks = stocksResult.data
            } else {
                errorLiveData.postValue(true)
                return@forEach
            }
        }
        Log.d("UpdateStocks", "$visibleStocks")
        companyStocksLiveData.postValue(currentStocks)
    }

    private suspend fun updateCompanyStock(
        currentStocks: MutableList<CompanyStock>,
        stock: CompanyStock
    ): Result<MutableList<CompanyStock>> {
        val index = currentStocks.indexOf(stock)
        if (index > -1) {
            val companyStockResult = loadCompanyStockInteractor.run(stock)
            return if (companyStockResult is Result.Success) {
                currentStocks[index] = companyStockResult.data
                Result.Success(currentStocks)
            } else {
                Result.Error((companyStockResult as Result.Error).exception)
            }
        }
        return Result.Error(ArrayIndexOutOfBoundsException())
    }

    class StocksViewModelFactory @Inject constructor(
        private val loadCompanyStocksInteractor: LoadCompanyStocksInteractor,
        private val loadCompanyStockInteractor: LoadCompanyStockInteractor
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return StocksViewModel(loadCompanyStocksInteractor, loadCompanyStockInteractor) as T
        }
    }

}