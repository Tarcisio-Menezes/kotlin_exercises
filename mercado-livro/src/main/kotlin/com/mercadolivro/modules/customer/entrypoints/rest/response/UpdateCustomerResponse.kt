package com.mercadolivro.modules.customer.entrypoints.rest.response

import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer

data class UpdateCustomerResponse(
    val name: String,
    val email: String
)

fun Customer.toUpdateAPIResponse(): UpdateCustomerResponse {
    return UpdateCustomerResponse(
        name = this.name,
        email = this.email
    )
}
