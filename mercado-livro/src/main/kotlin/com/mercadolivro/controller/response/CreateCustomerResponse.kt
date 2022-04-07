package com.mercadolivro.controller.response

import java.util.UUID

class CreateCustomerResponse(
    val identifier: UUID,
    val name: String,
    val email: String
)
