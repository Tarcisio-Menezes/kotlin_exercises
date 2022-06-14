package com.mercadolivro.controller.response

import com.mercadolivro.entitys.Customer

data class FindCustomerResponse(
    val name: String,
    val email: String
)

fun Customer.toGetResponse() = FindCustomerResponse(
    name = this.name,
    email = this.email
)

fun Collection<Customer>.toGetResponse() = this.map { it.toGetResponse() }
