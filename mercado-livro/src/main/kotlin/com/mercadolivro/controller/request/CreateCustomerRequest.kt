package com.mercadolivro.controller.request

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.Customer

data class CreateCustomerRequest(
    val name: String,
    val email: String,
)
{
    fun toCustomerModel(): Customer {
        return Customer(
            name = this.name,
            email = this.email,
            status = CustomerStatus.ENABLED
        )
    }
}
