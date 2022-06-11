package com.mercadolivro.controller.response

import com.mercadolivro.entitys.Customer

data class CreateCustomerResponse(
    val name: String,
    val email: String
)
fun Customer.toAPIResponse(): CreateCustomerResponse {
    return CreateCustomerResponse(
        name = this.name,
        email = this.email
    )
}
