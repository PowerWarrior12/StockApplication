package com.example.stockapplication.data.mappers

import com.example.stockapplication.data.api.entities.CompanyResponse
import com.example.stockapplication.domain.entities.Company
import com.example.stockapplication.domain.mappers.EntityMapper
import javax.inject.Inject

class CompanyResponseToCompanyMapper @Inject constructor(): EntityMapper<CompanyResponse, Company> {
    override fun map(entityFrom: CompanyResponse): Company {
        return Company(
            name = entityFrom.name,
            logo_path = entityFrom.logoPath
        )
    }
}