package com.mercadolivro.modules.book.entrypoints.rest.request

import com.mercadolivro.modules.book.enums.BookStatus

data class UpdateBookRequest(
    val name: String? = null,
    val price: Double? = null,
    val image: String? = null,
    val status: BookStatus? = null,
)
