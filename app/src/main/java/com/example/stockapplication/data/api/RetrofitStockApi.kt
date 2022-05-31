package com.example.stockapplication.data.api

import com.example.stockapplication.API_KEY
import com.example.stockapplication.EXCHANGE
import com.example.stockapplication.data.api.entities.CompanyResponse
import com.example.stockapplication.data.api.entities.StockPriceResponse
import com.example.stockapplication.data.api.entities.StockResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitStockApi {
    @GET("stock/symbol?token=$API_KEY&exchange=$EXCHANGE")
    suspend fun loadStockSymbolsForExchange(): Response<List<StockResponse>>

    @GET("quote?token=$API_KEY")
    suspend fun loadStockPriceBySymbol(@Query("symbol") symbol: String): Response<StockPriceResponse>

    @GET("stock/profile2?token=$API_KEY")
    suspend fun loadCompanyBySymbol(@Query("symbol") symbol: String): Response<CompanyResponse>
}