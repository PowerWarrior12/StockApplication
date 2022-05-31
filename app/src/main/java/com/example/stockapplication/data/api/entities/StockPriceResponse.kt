package com.example.stockapplication.data.api.entities

import com.squareup.moshi.Json

data class StockPriceResponse(
    @field:Json(name="c")
    val currentPrice: Float?,
    @field:Json(name="d")
    val changePrice: Float?,
    @field:Json(name="h")
    val highDayPrice: Float?,
    @field:Json(name="l")
    val lowDayPrice: Float?,

)
