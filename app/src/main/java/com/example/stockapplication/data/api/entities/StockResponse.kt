package com.example.stockapplication.data.api.entities

import com.squareup.moshi.Json

data class StockResponse(
    @field:Json(name="displaySymbol")
    val symbol: String,
    @field:Json(name="description")
    val name: String
)
