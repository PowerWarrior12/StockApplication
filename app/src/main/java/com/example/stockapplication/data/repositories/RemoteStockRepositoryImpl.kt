package com.example.stockapplication.data.repositories

import com.example.stockapplication.data.api.RetrofitStockApi
import com.example.stockapplication.data.api.entities.CompanyResponse
import com.example.stockapplication.data.api.entities.StockPriceResponse
import com.example.stockapplication.data.api.entities.StockResponse
import com.example.stockapplication.domain.entities.Company
import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.domain.entities.Price
import com.example.stockapplication.domain.mappers.EntityMapper
import com.example.stockapplication.domain.mappers.JoinEntitiesMapper
import com.example.stockapplication.domain.repositories.RemoteStockRepository
import com.example.stockapplication.utils.Result
import com.example.stockapplication.utils.Result.Error
import com.example.stockapplication.utils.Result.Success
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class RemoteStockRepositoryImpl @Inject constructor(
    private val stockApi: RetrofitStockApi,
    private val stockPriceResponseToPriceMapper: EntityMapper<StockPriceResponse, Price>,
    private val stockResponseToCompanyStockMapper: EntityMapper<StockResponse, CompanyStock>,
    private val companyResponseToCompanyMapper: EntityMapper<CompanyResponse, Company>
) : RemoteStockRepository {

    override suspend fun loadStocksWithoutPriceAndCompanyForExchange(): Result<List<CompanyStock>> {
        val stocksResult = loadResponse(stockApi.loadStockSymbolsForExchange())
        return if (stocksResult is Success) {
            val companyStocks = stocksResult.data.map { stockResponse ->
                stockResponseToCompanyStockMapper.map(stockResponse)
            }
            Success(companyStocks)
        } else {
            Error((stocksResult as Error).exception)
        }
    }

    override suspend fun loadStockPriceInformationBySymbol(symbol: String): Result<Price> {
        return loadResponse(stockApi.loadStockPriceBySymbol(symbol)).map { stockPriceResponse ->
            stockPriceResponseToPriceMapper.map(stockPriceResponse)
        }
    }

    override suspend fun loadCompanyBySymbol(symbol: String): Result<Company> {
        return loadResponse(stockApi.loadCompanyBySymbol(symbol)).map { companyResponse ->
            companyResponseToCompanyMapper.map(companyResponse)
        }
    }

    private fun <T> loadResponse(response: Response<T>): Result<T> {
        return try {
            if (response.isSuccessful) {
                Success(response.body()!!)
            } else {
                Error(Exception(response.message()))
            }
        } catch (exception: Exception) {
            Error(exception)
        }
    }
}