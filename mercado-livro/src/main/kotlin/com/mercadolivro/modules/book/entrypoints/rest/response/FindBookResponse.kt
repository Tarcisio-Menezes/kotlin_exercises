package com.mercadolivro.modules.book.entrypoints.rest.response

import com.mercadolivro.modules.book.dataproviders.jpa.entities.Book
import com.mercadolivro.modules.book.enums.BookStatus
import com.mercadolivro.modules.customer.entrypoints.rest.response.FindCustomerResponse
import com.mercadolivro.modules.customer.entrypoints.rest.response.toGetResponse
import java.time.Instant

data class FindBookResponse(
    val name: String,
    val status: BookStatus,
    val price: Double,
    val image: String?,
    val createdAt: Instant?,
    val updatedAt: Instant?,
    val customer: FindCustomerResponse
)

fun Book.toGetAPIResponse() = FindBookResponse(
    name = this.name,
    status = this.status,
    price = this.price,
    image = this.image,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    customer = this.customer.toGetResponse()
)

fun Collection<Book>.toGetAPIResponse() = this.map { it.toGetAPIResponse() }
