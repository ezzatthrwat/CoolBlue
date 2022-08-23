package com.example.shopping.di

import com.example.shopping.data.datasource.ProductsRemoteDataSource
import com.example.shopping.remote.api.ProductsApi
import com.example.shopping.remote.datasource.ProductsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
internal abstract class RemoteModule {

    companion object {
        @Provides
        @JvmStatic
        fun provideProductsApi(retrofit: Retrofit): ProductsApi {
            return retrofit.create(ProductsApi::class.java)
        }
    }

    @Binds
    abstract fun bindProductsRemoteDataSource(dataSource: ProductsRemoteDataSourceImpl): ProductsRemoteDataSource
}
