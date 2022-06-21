package com.mercadolivro.modules.customer.entrypoints.rest.request

import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer
import com.mercadolivro.modules.customer.enums.CustomerStatus

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
