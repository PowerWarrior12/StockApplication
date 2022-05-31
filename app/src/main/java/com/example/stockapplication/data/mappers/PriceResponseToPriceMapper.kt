package com.example.stockapplication.data.mappers

import com.example.stockapplication.data.api.entities.StockPriceResponse
import com.example.stockapplication.domain.entities.Price
import com.example.stockapplication.domain.mappers.EntityMapper
import javax.inject.Inject

class PriceResponseToPriceMapper @Inject constructor(): EntityMapper<StockPriceResponse, Price> {
    override fun map(entityFrom: StockPriceResponse): Price {
        return Price(
            currentPrice = entityFrom.currentPrice ?: 1000.0f
        )
    }
}