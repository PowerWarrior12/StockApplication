package com.example.stockapplication.domain.interactors

import com.example.stockapplication.domain.entities.Company
import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.domain.entities.Price
import com.example.stockapplication.domain.mappers.JoinEntitiesMapper
import com.example.stockapplication.domain.repositories.RemoteStockRepository
import com.example.stockapplication.utils.Result
import com.example.stockapplication.utils.Result.Success
import com.example.stockapplication.utils.Result.Error
import javax.inject.Inject

class LoadCompanyStockInteractor @Inject constructor(
    private val remoteStocksRepository: RemoteStockRepository,
    private val companyStockJoinPriceMapper: JoinEntitiesMapper<CompanyStock, Price, CompanyStock>,
    private val companyStockJoinCompanyMapper: JoinEntitiesMapper<CompanyStock, Result<Company>, CompanyStock>
) {
    suspend fun run(companyStock: CompanyStock): Result<CompanyStock> {
        var companyStockResult = companyStock
        val priceResult = remoteStocksRepository.loadStockPriceInformationBySymbol(companyStock.symbol)
        if (priceResult is Success) {
            companyStockResult = companyStockJoinPriceMapper.map(companyStockResult, priceResult.data)
        } else return Error((priceResult as Error).exception)
        val companyResult = remoteStocksRepository.loadCompanyBySymbol(companyStock.symbol)
        return Success(companyStockJoinCompanyMapper.map(companyStockResult, companyResult))
    }
}