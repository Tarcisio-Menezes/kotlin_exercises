package com.mercadolivro.modules.customer.entrypoints.rest.response

import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer

data class FindCustomerResponse(
    val name: String,
    val email: String
)

fun Customer.toGetResponse() = FindCustomerResponse(
    name = this.name,
    email = this.email
)

fun Collection<Customer>.toGetResponse() = this.map { it.toGetResponse() }
