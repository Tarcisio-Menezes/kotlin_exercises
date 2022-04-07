package com.mercadolivro.controller.request

import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import java.util.UUID

data class CreateBookRequest(
    val name: String,
    val identifier: UUID,
    val price: Float,
    val image: String,
    val customerIdentifier: UUID
) {
    fun toModelBook(customer: Customer): Book {
        return Book(
            name = this.name,
            identifier = this.identifier,
            price = this.price,
            image = this.image,
            customer = customer
        )
    }
}
