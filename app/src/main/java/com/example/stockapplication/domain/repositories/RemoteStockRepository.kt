package com.example.stockapplication.domain.repositories

import com.example.stockapplication.domain.entities.Company
import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.domain.entities.Price
import com.example.stockapplication.utils.Result

interface RemoteStockRepository {
    /**
     * Exchange must be specified in configurations
     */
    suspend fun loadStocksWithoutPriceAndCompanyForExchange(): Result<List<CompanyStock>>
    suspend fun loadStockPriceInformationBySymbol(symbol: String): Result<Price>
    suspend fun loadCompanyBySymbol(symbol: String): Result<Company>
}