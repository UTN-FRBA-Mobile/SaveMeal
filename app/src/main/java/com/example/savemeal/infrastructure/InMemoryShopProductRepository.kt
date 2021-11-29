package com.example.savemeal.infrastructure

import com.example.savemeal.domain.product.ShopProductDetail
import com.example.savemeal.domain.product.ShopProductRepository
import com.example.savemeal.domain.product.ShopProductService

class InMemoryShopProductRepository(private val shopProductService: ShopProductService) :
    ShopProductRepository {

    private val shopProducts: MutableList<ShopProductDetail> = mutableListOf()

    override suspend fun deleteProduct(productId: Int) {
        shopProducts.removeIf{it.id == productId }
        shopProductService.deleteProduct(productId)
    }

    override suspend fun createProduct( idComercio: Int, nombre: String,  detalle: String,  expiracion: String,  porciones: Int,  imagen: String) {
        shopProductService.createProduct(NewProduct(idComercio, nombre, detalle, expiracion, porciones, imagen))
    }

    data class NewProduct(val idComercio: Int,val nombre: String, val detalle: String, val expiracion: String, val porciones: Int, val imagen: String )
}