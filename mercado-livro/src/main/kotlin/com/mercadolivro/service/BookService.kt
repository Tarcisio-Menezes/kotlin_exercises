package com.mercadolivro.service

import com.mercadolivro.controller.request.CreateBookRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(
    val bookRepository: BookRepository,
    val customerService: CustomerService
) {

    fun create(book: CreateBookRequest): Book {
        kotlin.runCatching {
            customerService.getCustomerByIdentifier(book.customerIdentifier).let {
                return book.toModelBook(it)
            }
        }.getOrElse { throw Exception() }
    }

    fun findAll(name: String?): List<Book> {
        try {
            name?.let {
                return bookRepository.findByNameContaining(name)
            }
            return bookRepository.findAll().toList()
        } catch (e: Exception) {
            throw Exception()
        }
    }

    fun findByActive(): List<Book> {
        try {
            return bookRepository.findByStatus(BookStatus.ATIVO)
        } catch (e: Exception) {
            throw Exception()
        }
    }

    fun findById(identifier: UUID): Book {
        kotlin.runCatching {
            return bookRepository.findByIdentifier(identifier)!!
        }.getOrElse { throw Exception() }
    }

    fun delete(identifier: UUID) {
        kotlin.runCatching {
            return bookRepository.findByIdentifier(identifier).let {
                    book ->
                bookRepository.save(book!!.copy(status = BookStatus.CANCELADO))
            }
        }.getOrElse { throw Exception() }
    }

    fun update(book: Book): Book {
        try {
            bookRepository.save(book)
            return book
        } catch (e: Exception) {
            throw Exception()
        }
    }

    fun enable(identifier: UUID): Book {
        try {
            bookRepository.findByIdentifier(identifier).apply {
                bookRepository.save(this!!.copy(status = BookStatus.ATIVO))
                return this
            }
        } catch (e: Exception) {
            throw Exception()
        }
    }

    fun deleteByCustomer(customer: Customer) {
        kotlin.runCatching {
            val books = bookRepository.findByCustomer(customer)
            books.map {
                    book -> bookRepository.save(book.copy(status = BookStatus.DELETADO))
            }
        }.getOrElse { throw Exception() }
    }
}
