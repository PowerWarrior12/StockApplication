package com.example.stockapplication.data.mappers

import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.domain.entities.Price
import com.example.stockapplication.domain.mappers.JoinEntitiesMapper
import javax.inject.Inject

class CompanyStockJoinPriceMapper @Inject constructor() : JoinEntitiesMapper<CompanyStock, Price, CompanyStock> {
    override fun map(entityFromFirst: CompanyStock, entityFromSecond: Price): CompanyStock {
        return entityFromFirst.copy(price = entityFromSecond.currentPrice)
    }
}