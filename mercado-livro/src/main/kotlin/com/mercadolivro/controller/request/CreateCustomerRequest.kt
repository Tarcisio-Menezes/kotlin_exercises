package com.mercadolivro.controller.request

import com.mercadolivro.entitys.Customer
import com.mercadolivro.enums.CustomerStatus

data class CreateCustomerRequest(
    val name: String,
    val email: String,
) {
    fun toCustomerModel(): Customer {
        return Customer(
            name = this.name,
            email = this.email,
            status = CustomerStatus.ENABLED
        )
    }
}
