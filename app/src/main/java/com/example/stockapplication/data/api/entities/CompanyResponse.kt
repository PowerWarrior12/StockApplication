package com.example.stockapplication.data.api.entities

import com.squareup.moshi.Json

data class CompanyResponse(
    @field:Json(name="ticker")
    val symbol: String,
    @field:Json(name="name")
    val name: String,
    @field:Json(name="logo")
    val logoPath: String
)
