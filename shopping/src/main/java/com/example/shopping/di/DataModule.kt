package com.example.shopping.di

import com.example.shopping.data.repository.ProductsRepositoryImpl
import com.example.shopping.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindProductsRepository(repository: ProductsRepositoryImpl): ProductsRepository
}
