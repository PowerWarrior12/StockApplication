package com.example.stockapplication.data.mappers

import com.example.stockapplication.data.api.entities.StockResponse
import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.domain.mappers.EntityMapper
import javax.inject.Inject

class StockResponseToCompanyStockMapper @Inject constructor(): EntityMapper<StockResponse, CompanyStock> {
    override fun map(entityFrom: StockResponse): CompanyStock {
        return CompanyStock(
            symbol = entityFrom.symbol,
            title = entityFrom.name
        )
    }
}