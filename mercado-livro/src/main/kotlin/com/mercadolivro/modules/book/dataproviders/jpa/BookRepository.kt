package com.mercadolivro.modules.book.dataproviders.jpa

import com.mercadolivro.modules.book.dataproviders.jpa.entities.Book
import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer
import com.mercadolivro.modules.book.enums.BookStatus
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Int> {
    fun findByNameContaining(name: String): Collection<Book>
    fun findByStatus(status: BookStatus): Collection<Book>
    fun findByCustomer(customer: Customer): Collection<Book>
    fun findBookById(id: Int): Book?
}
