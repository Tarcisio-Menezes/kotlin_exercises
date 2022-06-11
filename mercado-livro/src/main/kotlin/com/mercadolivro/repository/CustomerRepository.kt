package com.mercadolivro.repository

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.entitys.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Int> {
    fun findByNameContaining(name: String): List<Customer>
    fun findByStatus(status: CustomerStatus): List<Customer>
    fun findCustomerById(id: Int): Customer?
    fun findByEmail(email: String): Customer?
}
