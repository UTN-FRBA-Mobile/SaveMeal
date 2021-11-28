package com.example.savemeal.domain.product;

data class ShopProductDetail (
        val id: Int,
        val nombre: String,
        val detalle: String,
        val expiracion: String,
        val idComercio: Int,
        val porciones: Int,
        val disponibles: Int,
        val imagen: String,
)
