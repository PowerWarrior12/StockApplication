package com.example.stockapplication.data.api

import com.example.stockapplication.API_KEY
import com.example.stockapplication.WEB_SOCKET_URL
import com.example.stockapplication.data.api.entities.StockResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.http.GET

class StockApi {
    fun getStocksRequest(): Request {
        return Request.Builder().url("wss://ws.finnhub.io?token=ca8u3l2ad3icsujbec5g").build()
    }

}