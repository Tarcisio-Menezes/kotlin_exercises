package com.mercadolivro.repository

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.Customer
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CustomerRepository : CrudRepository<Customer, Int> {

    fun findByNameContaining(name: String): List<Customer>
    fun findByStatus(status: CustomerStatus): List<Customer>
    fun findByIdentifier(identifier: UUID): Customer?
}