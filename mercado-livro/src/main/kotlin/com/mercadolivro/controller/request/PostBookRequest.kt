package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias

data class PostBookRequest(
    var name: String,
    var price: Float,

    @JsonAlias("customer_id")
    var customerId: Int
)