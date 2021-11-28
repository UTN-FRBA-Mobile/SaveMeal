package com.example.savemeal.domain.product

import com.example.savemeal.ShopProducts.ShopProduct

interface ShopProductRepository {

    suspend fun getShopProducts(): List<ShopProduct>
    fun getProductDetail(productId: Int): ShopProductDetail
    suspend fun deleteProduct(productId: Int)
}