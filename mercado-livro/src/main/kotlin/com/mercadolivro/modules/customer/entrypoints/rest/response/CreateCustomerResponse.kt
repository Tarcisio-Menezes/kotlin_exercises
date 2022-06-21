package com.mercadolivro.modules.customer.entrypoints.rest.response

import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer

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
