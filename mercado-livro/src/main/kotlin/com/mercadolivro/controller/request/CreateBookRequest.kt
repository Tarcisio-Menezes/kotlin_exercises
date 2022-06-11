package com.mercadolivro.controller.request

import com.mercadolivro.entitys.Book
import com.mercadolivro.entitys.Customer
import java.time.Instant

data class CreateBookRequest(
    val name: String,
    val price: Double,
    val image: String,
    val customerId: Int
) {
    fun toModelBook(customer: Customer): Book {
        return Book(
            name = this.name,
            price = this.price,
            image = this.image,
            customer = customer,
            createdAt = Instant.now()
        )
    }
}
