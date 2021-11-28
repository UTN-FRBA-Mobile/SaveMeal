package com.example.savemeal.domain.meal

data class MealDetail(
    val id: Int,
    val nombre: String,
    val detalle: String,
    val disponibles: Int,
    val expiracion: String,
    val porciones: String,
    val imagen: String,
    val business: Business
)
