package com.example.savemeal.domain.product

import androidx.lifecycle.ViewModel
import com.example.savemeal.ShopProductDI

class ShopProductViewModel: ViewModel() {

    private val shopProductRepository: ShopProductRepository = ShopProductDI.provideShopProductRepository()

    suspend fun createProduct(product: ShopProductDetail) {
        shopProductRepository.createProduct(product.idComercio,product.nombre,product.detalle,product.expiracion,product.porciones,product.imagen)
    }

    suspend fun deleteProduct(productId: Int){
        shopProductRepository.deleteProduct(productId)
    }
}