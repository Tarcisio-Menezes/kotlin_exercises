package com.mercadolivro.controller.request

import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer

data class CreateBookRequest(
    val name: String,
    val price: Int,
    val image: String,
    val customerId: Int
) {
    fun toModelBook(customer: Customer): Book {
        return Book(
            name = this.name,
            price = this.price,
            image = this.image,
            customer = customer
        )
    }
}
