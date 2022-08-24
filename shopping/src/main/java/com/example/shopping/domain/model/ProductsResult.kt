package com.example.shopping.domain.model

data class ProductsResult(
    val currentPage: Int,
    val pageCount: Int,
    val products: List<Product>,
)