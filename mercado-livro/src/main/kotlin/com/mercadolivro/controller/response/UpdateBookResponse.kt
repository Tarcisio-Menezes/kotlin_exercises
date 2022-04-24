package com.mercadolivro.controller.response

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import java.util.UUID

class UpdateBookResponse(
    val name: String,
    val id: Int,
    val status: BookStatus,
    val price: Int,
    val image: String,
    val customer: Customer
)

fun Book.toUpdateAPIResponse() = UpdateBookResponse(
    name = this.name,
    id = this.id!!,
    status = this.status!!,
    price = this.price,
    image = this.image,
    customer = this.customer!!
)
