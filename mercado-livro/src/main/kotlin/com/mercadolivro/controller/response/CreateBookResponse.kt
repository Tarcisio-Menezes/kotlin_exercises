package com.mercadolivro.controller.response

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import java.util.UUID

class CreateBookResponse(
    val name: String,
    val identifier: UUID,
    val status: BookStatus,
    val price: Float,
    val image: String,
    val customerIdentifier: UUID
)

fun Book.toCreateAPIResponse() = CreateBookResponse(
    name = this.name,
    identifier = this.identifier,
    status = this.status!!,
    price = this.price,
    image = this.image,
    customerIdentifier = this.customer!!.identifier
)
