package com.example.stockapplication.presentation.di

import com.example.stockapplication.data.api.RetrofitService
import com.example.stockapplication.data.api.RetrofitStockApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RemoteServiceModule {

    @Provides
    fun provideRetrofitService(): Retrofit {
        return RetrofitService().generateRetrofit()
    }

    @Provides
    fun provideRetrofitStockApi(retrofit: Retrofit): RetrofitStockApi {
        return retrofit.create(RetrofitStockApi::class.java)
    }

}