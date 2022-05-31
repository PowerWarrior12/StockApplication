package com.example.stockapplication.data.mappers

import com.example.stockapplication.domain.entities.Company
import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.domain.mappers.JoinEntitiesMapper
import com.example.stockapplication.utils.Result.Success
import com.example.stockapplication.utils.Result
import javax.inject.Inject

class CompanyStockJoinCompanyMapper @Inject constructor() :
    JoinEntitiesMapper<CompanyStock, Result<@JvmSuppressWildcards Company>, CompanyStock> {
    override fun map(entityFromFirst: CompanyStock, entityFromSecond: Result<Company>): CompanyStock {
        return if (entityFromSecond is Success) {
            entityFromFirst.copy(logoPath = entityFromSecond.data.logo_path)
        } else {
            entityFromFirst
        }
    }
}