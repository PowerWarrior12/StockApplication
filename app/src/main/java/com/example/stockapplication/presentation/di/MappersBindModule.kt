package com.example.stockapplication.presentation.di

import com.example.stockapplication.data.api.entities.CompanyResponse
import com.example.stockapplication.data.api.entities.StockPriceResponse
import com.example.stockapplication.data.api.entities.StockResponse
import com.example.stockapplication.data.mappers.*
import com.example.stockapplication.domain.entities.Company
import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.domain.entities.Price
import com.example.stockapplication.domain.mappers.EntityMapper
import com.example.stockapplication.domain.mappers.JoinEntitiesMapper
import com.example.stockapplication.utils.Result
import dagger.Binds
import dagger.Module

@Module
abstract class MappersBindModule {

    @Binds
    abstract fun bindPriceResponseToPriceMapper(
        priceResponseToPriceMapper: PriceResponseToPriceMapper
    ): EntityMapper<StockPriceResponse, Price>

    @Binds
    abstract fun bindCompanyResponseToCompanyMapper(
        companyResponseToCompanyMapper: CompanyResponseToCompanyMapper
    ): EntityMapper<CompanyResponse, Company>

    @Binds
    abstract fun bindCompanyStockJoinCompanyMapper(
        companyStockJoinCompanyMapper: CompanyStockJoinCompanyMapper
    ): JoinEntitiesMapper<CompanyStock, Result<Company>, CompanyStock>

    @Binds
    abstract fun bindCompanyStockJoinPriceMapper(
        companyStockJoinPriceMapper: CompanyStockJoinPriceMapper
    ) : JoinEntitiesMapper<CompanyStock, Price, CompanyStock>

    @Binds
    abstract fun bindStockResponseToCompanyStockMapper(
        stockResponseToCompanyStockMapper: StockResponseToCompanyStockMapper
    ): EntityMapper<StockResponse, CompanyStock>

}