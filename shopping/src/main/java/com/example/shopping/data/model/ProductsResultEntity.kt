package com.example.shopping.data.model

data class ProductsResultEntity(
    val currentPage: Int,
    val pageCount: Int,
    val productEntities: List<ProductEntity>,
)
