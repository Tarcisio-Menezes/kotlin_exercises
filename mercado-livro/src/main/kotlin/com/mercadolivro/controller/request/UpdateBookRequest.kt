package com.mercadolivro.controller.request

import com.mercadolivro.enums.BookStatus

data class UpdateBookRequest(
    val name: String? = null,
    val price: Double? = null,
    val image: String? = null,
    val status: BookStatus? = null,
)
