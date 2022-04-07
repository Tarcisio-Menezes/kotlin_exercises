package com.mercadolivro.repository

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import org.springframework.data.repository.CrudRepository
import java.util.*

interface BookRepository : CrudRepository<Book, Int> {
    fun findByNameContaining(name: String): List<Book>
    fun findByStatus(status: BookStatus): List<Book>
    fun findByCustomer(customer: Customer): List<Book>
    fun findByIdentifier(identifier: UUID): Book?
}
