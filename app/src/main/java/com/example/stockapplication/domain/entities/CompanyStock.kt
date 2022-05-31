package com.example.stockapplication.domain.entities

data class CompanyStock(
    val symbol: String,
    val title: String,
    val price: Float? = null,
    val logoPath: String? = null
)