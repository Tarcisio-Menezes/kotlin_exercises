package com.mercadolivro.modules.book.entrypoints.rest.request

import com.mercadolivro.modules.book.dataproviders.jpa.entities.Book
import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer
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
