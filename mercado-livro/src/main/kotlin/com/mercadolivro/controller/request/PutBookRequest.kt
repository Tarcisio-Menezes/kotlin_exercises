package com.mercadolivro.controller.request

data class PutBookRequest(
    var name: String? = null,
    var price: Float? = null,
)