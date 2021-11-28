package com.example.savemeal.domain.product

import androidx.lifecycle.ViewModel
import com.example.savemeal.ShopProductDI
import com.example.savemeal.domain.meal.MealDetail

class ShopProductViewModel: ViewModel() {

    private val shopProductRepository: ShopProductRepository = ShopProductDI.provideShopProductRepository()

    fun getProductDetail(productId: Int): ShopProductDetail {
        return shopProductRepository.getProductDetail(productId)
    }

    suspend fun createProduct(product: ShopProductDetail) {
        shopProductRepository.createProduct(product.idComercio,product.nombre,product.detalle,product.expiracion,product.porciones,product.imagen)
    }

    suspend fun deleteProduct(productId: Int){
        shopProductRepository.deleteProduct(productId)
    }
}