package com.mercadolivro.controller.request

import com.mercadolivro.controller.response.CreateCustomerResponse
import com.mercadolivro.model.Customer
import java.util.UUID

data class CreateCustomerRequest(
    var name: String,
    var email: String,
)

fun Customer.toCustomerModel(): CreateCustomerResponse {
    return CreateCustomerResponse(
        identifier = UUID.randomUUID(),
        name = this.name,
        email = this.email
    )
}