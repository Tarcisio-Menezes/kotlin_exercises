package com.mercadolivro.controller.response

import com.mercadolivro.model.Customer

data class UpdateCustomerResponse(
    val id: Int,
    val name: String,
    val email: String
)

fun Customer.toUpdateAPIResponse(): UpdateCustomerResponse {
   return UpdateCustomerResponse(
       id = this.id!!,
       name = this.name,
       email = this.email
   )
}
