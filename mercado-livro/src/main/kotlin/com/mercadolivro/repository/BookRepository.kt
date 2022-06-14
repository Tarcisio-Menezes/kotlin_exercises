package com.mercadolivro.repository

import com.mercadolivro.entitys.Book
import com.mercadolivro.entitys.Customer
import com.mercadolivro.enums.BookStatus
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {
    fun findByNameContaining(name: String): Collection<Book>
    fun findByStatus(status: BookStatus): Collection<Book>
    fun findByCustomer(customer: Customer): Collection<Book>
    fun findBookById(id: Int): Book?
}
