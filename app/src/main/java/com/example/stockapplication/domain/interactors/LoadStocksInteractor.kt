package com.example.stockapplication.domain.interactors

import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.domain.repositories.RemoteStockRepository
import com.example.stockapplication.utils.Result
import javax.inject.Inject

class LoadCompanyStocksInteractor @Inject constructor(
    private val remoteStockRepository: RemoteStockRepository
) {
    suspend fun run(): Result<List<CompanyStock>> {
        return remoteStockRepository.loadStocksWithoutPriceAndCompanyForExchange()
    }
}