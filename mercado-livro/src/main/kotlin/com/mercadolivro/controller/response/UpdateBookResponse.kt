package com.mercadolivro.controller.response

import com.mercadolivro.entitys.Book
import com.mercadolivro.entitys.Customer
import com.mercadolivro.enums.BookStatus

data class UpdateBookResponse(
    val name: String,
    val id: Int,
    val status: BookStatus,
    val price: Double,
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
