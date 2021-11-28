package com.example.savemeal.domain.product

import androidx.lifecycle.ViewModel
import com.example.savemeal.ShopProductDI

class ShopProductViewModel: ViewModel() {

    private val shopProductRepository: ShopProductRepository = ShopProductDI.provideShopProductRepository()

    fun getProductDetail(productId: Int): ShopProductDetail {
        return shopProductRepository.getProductDetail(productId)
    }

    suspend fun deleteProduct(productId: Int){
        shopProductRepository.deleteProduct(productId)
    }
}