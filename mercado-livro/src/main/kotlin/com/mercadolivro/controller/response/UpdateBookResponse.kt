package com.mercadolivro.controller.response

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import java.util.UUID

class UpdateBookResponse(
    val name: String,
    val identifier: UUID,
    val status: BookStatus,
    val price: Float,
    val image: String,
    val customer: Customer
)

fun Book.toUpdateAPIResponse() = UpdateBookResponse(
    name = this.name,
    identifier = this.identifier,
    status = this.status!!,
    price = this.price,
    image = this.image,
    customer = this.customer!!
)
