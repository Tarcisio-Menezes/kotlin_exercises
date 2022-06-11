package com.mercadolivro.controller.request

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.entitys.Customer

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
