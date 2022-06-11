package com.mercadolivro.controller.response

import com.mercadolivro.entitys.Customer

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
