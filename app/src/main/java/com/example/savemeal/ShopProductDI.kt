package com.example.savemeal;

import com.example.savemeal.domain.product.ShopProductRepository
import com.example.savemeal.domain.product.ShopProductService
import com.example.savemeal.infrastructure.InMemoryShopProductRepository

object ShopProductDI {

    private val shopProductService = ShopProductService.create()
    private val shopProductRepository = InMemoryShopProductRepository(shopProductService)

    fun provideShopProductRepository(): ShopProductRepository = shopProductRepository
}
