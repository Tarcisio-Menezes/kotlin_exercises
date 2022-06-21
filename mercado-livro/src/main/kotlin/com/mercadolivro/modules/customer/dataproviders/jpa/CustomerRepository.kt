package com.mercadolivro.modules.customer.dataproviders.jpa

import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer
import com.mercadolivro.modules.customer.enums.CustomerStatus
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Int> {
    fun findByNameContaining(name: String): List<Customer>
    fun findByStatus(status: CustomerStatus): List<Customer>
    fun findCustomerById(id: Int): Customer?
    fun findByEmail(email: String): Customer?
}
