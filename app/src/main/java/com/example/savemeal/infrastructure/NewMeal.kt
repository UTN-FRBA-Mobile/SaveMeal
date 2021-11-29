package com.example.savemeal.infrastructure

data class NewMeal(
    val idComercio: Int = 1,
    val nombre: String,
    val detalle: String,
    val expiracion: String,
    val porciones: Int,
    val imagen: String
)