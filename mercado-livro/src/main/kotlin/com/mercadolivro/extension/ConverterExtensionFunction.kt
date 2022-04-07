package com.mercadolivro.extension

import com.mercadolivro.controller.request.CreateBookRequest
import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateBookRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.controller.response.CreateBookResponse
import com.mercadolivro.controller.response.CreateCustomerResponse
import com.mercadolivro.controller.response.UpdateBookResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import java.util.*

fun CreateCustomerRequest.toCustomerModel(): CreateCustomerResponse {
    return CreateCustomerResponse(
        identifier = UUID.randomUUID(),
        name = this.name,
        email = this.email
    )
}

fun UpdateCustomerRequest.toCustomerModel(): UpdateCustomerRequest {
    return UpdateCustomerRequest(
        name = this.name,
        email = this.email
    )
}

fun UpdateBookRequest.toBookModel(oldBook: Book): UpdateBookResponse {
    return UpdateBookResponse(
        identifier = oldBook.identifier,
        name = this.name ?: oldBook.name,
        price = this.price ?: oldBook.price,
        status = oldBook.status!!,
        image = this.image ?: oldBook.image,
        customer = oldBook.customer!!
    )
}
