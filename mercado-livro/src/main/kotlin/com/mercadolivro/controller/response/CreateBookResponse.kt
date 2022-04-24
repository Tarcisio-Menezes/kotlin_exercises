package com.mercadolivro.controller.response

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import java.util.UUID

class CreateBookResponse(
    val name: String,
    val status: BookStatus,
    val price: Int,
    val image: String,
    val customerId: Int
)

fun Book.toCreateAPIResponse() = CreateBookResponse(
    name = this.name,
    status = this.status!!,
    price = this.price,
    image = this.image,
    customerId = this.customer!!.id!!
)
