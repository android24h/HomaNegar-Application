package com.example.myapplication.di

import com.example.myapplication.data.repository.InternetServiceRepositoryImpl
import com.example.myapplication.data.repository.NetRepositoryImpl
import com.example.myapplication.data.repository.PrintRepositoryImpl
import com.example.myapplication.data.repository.ProductRepositoryImpl
import com.example.myapplication.data.repository.SaleRepositoryImpl
import com.example.myapplication.domain.repository.InternetServiceRepository
import com.example.myapplication.domain.repository.NetRepository
import com.example.myapplication.domain.repository.PrintRepository
import com.example.myapplication.domain.repository.ProductRepository
import com.example.myapplication.domain.repository.SaleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNetRepository(
        repositoryImpl: NetRepositoryImpl
    ): NetRepository


    @Binds
    @Singleton
    abstract fun bindPrintRepository(
        repositoryImpl: PrintRepositoryImpl
    ): PrintRepository

    @Binds
    @Singleton
    abstract fun bindLavazemRepositorySale(repository: SaleRepositoryImpl): SaleRepository

    @Binds
    @Singleton
    abstract fun bindLavazemRepositoryProduct(repository: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    abstract fun bindInternetServiceRepository(repository: InternetServiceRepositoryImpl): InternetServiceRepository
}

