package com.mercadolivro.controller.request

data class UpdateBookRequest(
    val name: String? = null,
    val price: Float? = null,
    val image: String? = null,
)
