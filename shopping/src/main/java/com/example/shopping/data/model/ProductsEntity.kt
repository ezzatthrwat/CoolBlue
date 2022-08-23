package com.example.shopping.data.model

data class ProductsEntity(
    val currentPage: Int,
    val pageCount: Int,
    val productEntities: List<ProductEntity>,
)