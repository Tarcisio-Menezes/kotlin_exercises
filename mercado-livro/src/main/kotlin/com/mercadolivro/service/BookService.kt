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
        runCatching {
            customerService.getCustomerByIdentifier(book.customerIdentifier).let {
                return book.toModelBook(it)
            }
        }.getOrElse { throw Exception() }
    }

    fun findAll(name: String?): List<Book> {
        runCatching {
            name?.let {
                return bookRepository.findByNameContaining(name)
            }
            return bookRepository.findAll().toList()
        }.getOrElse {
            throw Exception()
        }
    }

    fun findByActive(): List<Book> {
        runCatching {
            return bookRepository.findByStatus(BookStatus.ATIVO)
        }.getOrElse {
            throw Exception()
        }
    }

    fun findById(identifier: UUID): Book {
        runCatching {
            return bookRepository.findByIdentifier(identifier)!!
        }.getOrElse { throw Exception() }
    }

    fun delete(identifier: UUID) {
        runCatching {
            return bookRepository.findByIdentifier(identifier).let {
                    book ->
                bookRepository.save(book!!.copy(status = BookStatus.CANCELADO))
            }
        }.getOrElse { throw Exception() }
    }

    fun update(book: Book): Book {
        runCatching{
            bookRepository.save(book)
            return book
        }.getOrElse {
            throw Exception()
        }
    }

    fun enable(identifier: UUID): Book? {
        return runCatching {
            bookRepository.findByIdentifier(identifier).apply {
                bookRepository.save(this!!.copy(status = BookStatus.ATIVO))
            }
        }.getOrElse { throw Exception() }
    }

    fun deleteByCustomer(customer: Customer) {
        runCatching {
            val books = bookRepository.findByCustomer(customer)
            books.map {
                    book -> bookRepository.save(book.copy(status = BookStatus.DELETADO))
            }
        }.getOrElse { throw Exception() }
    }
}
