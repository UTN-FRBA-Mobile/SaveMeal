package com.example.savemeal.infrastructure

import com.example.savemeal.ShopProducts.ShopProduct
import com.example.savemeal.domain.product.ShopProductDetail
import com.example.savemeal.domain.product.ShopProductRepository
import com.example.savemeal.domain.product.ShopProductService

class InMemoryShopProductRepository(private val shopProductService: ShopProductService) :
    ShopProductRepository {

    private val shopProducts: MutableList<ShopProductDetail> = mutableListOf()

    private fun addMissingShopProducts(newShopProducts: List<ShopProductDetail>) {
        val missingReservations = newShopProducts.filterNot {
            shopProducts.any { res ->
                res.id == it.id
            }
        }

        shopProducts.addAll(missingReservations)
    }


    override suspend fun getShopProducts(): List<ShopProduct> {
        addMissingShopProducts(shopProductService.getShopProducts())
        return shopProducts.toShopProductOption()
    }

    override fun getProductDetail(productId: Int): ShopProductDetail {
        return shopProducts.find { it.id == productId }!!
    }

    override suspend fun deleteProduct(productId: Int) {
        shopProducts.removeIf{it.id == productId }
        shopProductService.deleteProduct(productId)
    }

    override suspend fun createProduct( idComercio: Int, nombre: String,  detalle: String,  expiracion: String,  porciones: Int,  imagen: String) {
        shopProductService.createProduct(NewProduct(idComercio, nombre, detalle, expiracion, porciones, imagen))
    }

    private fun MutableList<ShopProductDetail>.toShopProductOption(): List<ShopProduct> {
        return this.map { ShopProduct(it.id, it.nombre) }
    }

    data class NewProduct(val idComercio: Int,val nombre: String, val detalle: String, val expiracion: String, val porciones: Int, val imagen: String )
}