package com.mercadolivro.controller.response

import com.mercadolivro.entitys.Book
import com.mercadolivro.enums.BookStatus

class CreateBookResponse(
    val name: String,
    val status: BookStatus,
    val price: Double,
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
