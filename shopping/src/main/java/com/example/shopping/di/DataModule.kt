package com.example.shopping.di

import com.example.shopping.data.repository.ProductsRepositoryImpl
import com.example.shopping.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
internal abstract class DataModule {

    @ActivityScoped
    @Binds
    abstract fun bindProductsRepository(repository: ProductsRepositoryImpl): ProductsRepository

}
