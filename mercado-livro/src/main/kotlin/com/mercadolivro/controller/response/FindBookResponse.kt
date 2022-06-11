package com.mercadolivro.controller.response

import com.mercadolivro.entitys.Book
import com.mercadolivro.entitys.Customer
import com.mercadolivro.enums.BookStatus
import java.time.Instant

data class FindBookResponse(
    val name: String,
    val status: BookStatus,
    val price: Double,
    val image: String,
    val createdAt: Instant?,
    val updatedAt: Instant?,
    val customer: Customer
)

fun Book.toGetAPIResponse() = FindBookResponse(
    name = this.name,
    status = this.status!!,
    price = this.price,
    image = this.image,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    customer = this.customer!!
)

fun Collection<Book>.toGetAPIResponse() = this.map { it.toGetAPIResponse() }
