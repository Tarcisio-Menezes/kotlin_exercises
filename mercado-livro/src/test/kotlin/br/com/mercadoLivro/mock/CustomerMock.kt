package br.com.mercadoLivro.mock

import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer
import com.mercadolivro.modules.customer.enums.CustomerStatus.*

class CustomerMock {

    companion object {
        fun validCustomer() = Customer(
            id = 1,
            name = "Ramón Valdez",
            email = "madruguinha@fakemail.com",
            status = ENABLED
        )

        fun invalidCustomer() = Customer(
            id = 1,
            name = "Ramón Valdez",
            email = "madruguinha@fakemail.com",
            status = DISABLED
        )
    }
}
