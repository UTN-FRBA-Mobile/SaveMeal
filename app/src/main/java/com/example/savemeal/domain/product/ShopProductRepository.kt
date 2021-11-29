package com.example.savemeal.domain.product

interface ShopProductRepository {

    suspend fun deleteProduct(productId: Int)
    suspend fun createProduct( idComercio: Int, nombre: String,  detalle: String,  expiracion: String,  porciones: Int,  imagen: String)

}