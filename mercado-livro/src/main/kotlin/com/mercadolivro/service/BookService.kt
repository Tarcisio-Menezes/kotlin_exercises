package com.mercadolivro.service

import com.mercadolivro.controller.request.CreateBookRequest
import com.mercadolivro.controller.request.UpdateBookRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository,
    val customerRepository: CustomerRepository
) {

    fun create(book: CreateBookRequest): Book {
        runCatching {
            customerRepository.findCustomerById(book.customerId).let {
                return book.toModelBook(it!!)
            }
        }.getOrElse { throw Exception() }
    }

    fun findAll(name: String?): Collection<Book> {
        runCatching {
            name?.let {
                return bookRepository.findByNameContaining(name)
            }
            return bookRepository.findAll().toList()
        }.getOrElse {
            throw Exception()
        }
    }

    fun findByActive(): Collection<Book> {
        runCatching {
            return bookRepository.findByStatus(BookStatus.ATIVO)
        }.getOrElse {
            throw Exception()
        }
    }

    fun findById(id: Int): Book {
        runCatching {
            return bookRepository.findBookById(id)!!
        }.getOrElse { throw Exception() }
    }

    fun delete(id: Int) {
        runCatching {
            return bookRepository.findBookById(id).let {
                    book ->
                bookRepository.save(book!!.copy(status = BookStatus.CANCELADO))
            }
        }.getOrElse { throw Exception() }
    }

    fun update(id: Int, book: UpdateBookRequest): Book {
        return runCatching{
            findById(id).apply {
                bookRepository.save(this.copy(
                    name = book.name ?: this.name,
                    image = book.image ?: this.image,
                    price = book.price ?: this.price,

                ))
            }
        }.getOrElse {
            throw Exception()
        }
    }

    fun enable(identifier: Int): Book? {
        return runCatching {
            bookRepository.findBookById(identifier).apply {
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
