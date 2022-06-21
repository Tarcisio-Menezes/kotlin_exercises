package com.mercadolivro.modules.book.entrypoints.rest.response

import com.mercadolivro.modules.book.dataproviders.jpa.entities.Book
import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer
import com.mercadolivro.modules.book.enums.BookStatus

data class UpdateBookResponse(
    val name: String,
    val status: BookStatus,
    val price: Double,
    val image: String?,
    val customer: Customer
)

fun Book.toUpdateAPIResponse() = UpdateBookResponse(
    name = this.name,
    status = this.status,
    price = this.price,
    image = this.image,
    customer = this.customer
)
