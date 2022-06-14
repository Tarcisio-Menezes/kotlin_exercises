package br.com.mercadoLivro.mock

import com.mercadolivro.entitys.Customer
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.CustomerStatus.*

class CustomerMock {

    companion object {
        fun validCustomer() = Customer(
            id =1,
            name = "Ramón Valdez",
            email = "madruguinha@fakemail.com",
            status = ENABLED
        )

        fun invalidCustomer() = Customer(
            id =1,
            name = "Ramón Valdez",
            email = "madruguinha@fakemail.com",
            status = DISABLED
        )
    }
}