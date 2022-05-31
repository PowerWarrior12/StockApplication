package com.example.stockapplication.presentation.di

import com.example.stockapplication.data.repositories.RemoteStockRepositoryImpl
import com.example.stockapplication.domain.repositories.RemoteStockRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesBindModule {

    @Binds
    abstract fun bindRemoteStockRepository(remoteStockRepositoryImpl: RemoteStockRepositoryImpl): RemoteStockRepository

}