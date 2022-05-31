package com.example.stockapplication.presentation.di

import dagger.Module

@Module(includes = [NavigationModule::class, MappersBindModule::class, RemoteServiceModule::class, RepositoriesBindModule::class])
class MainModule