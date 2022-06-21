package com.mercadolivro.modules.book.entrypoints.rest.response

import com.mercadolivro.modules.book.dataproviders.jpa.entities.Book
import com.mercadolivro.modules.book.enums.BookStatus
import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer

class CreateBookResponse(
    val name: String,
    val status: BookStatus,
    val price: Double,
    val image: String?,
    val customer: Customer
)

fun Book.toCreateAPIResponse() = CreateBookResponse(
    name = this.name,
    status = this.status,
    price = this.price,
    image = this.image,
    customer = this.customer
)
