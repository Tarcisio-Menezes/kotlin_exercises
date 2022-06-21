package com.mercadolivro.modules.customer.entrypoints.rest.request

data class UpdateCustomerRequest(
    var name: String,
    var email: String,
)
