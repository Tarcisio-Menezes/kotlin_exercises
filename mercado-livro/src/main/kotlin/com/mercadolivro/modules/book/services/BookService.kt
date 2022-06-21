package com.mercadolivro.modules.book.services

import com.mercadolivro.modules.book.entrypoints.rest.request.CreateBookRequest
import com.mercadolivro.modules.book.entrypoints.rest.request.UpdateBookRequest
import com.mercadolivro.modules.book.dataproviders.jpa.entities.Book
import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer
import com.mercadolivro.modules.book.enums.BookStatus
import com.mercadolivro.modules.book.exceptions.BookCreationException
import com.mercadolivro.modules.book.exceptions.BookDeleteException
import com.mercadolivro.modules.book.exceptions.BookEnabledException
import com.mercadolivro.modules.book.exceptions.BookGetException
import com.mercadolivro.modules.book.exceptions.BookUpdateException
import com.mercadolivro.modules.book.dataproviders.jpa.BookRepository
import com.mercadolivro.modules.customer.dataproviders.jpa.CustomerRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class BookService(
    val bookRepository: BookRepository,
    val customerRepository: CustomerRepository
) {

    private val notFindBook = "Could not find book"
    private val notFindAssociateCustomer = "Could not find associate customer to book"

    fun create(book: CreateBookRequest): Book {
        return runCatching {
            customerRepository.findCustomerById(book.customerId).let {
                bookRepository.save(book.toModelBook(it!!))
            }
        }.getOrElse { throw BookCreationException(notFindAssociateCustomer) }
    }

    fun findAll(name: String?): Collection<Book> {
        return runCatching {
            name?.let {
                bookRepository.findByNameContaining(name)
            } ?: bookRepository.findAll().toList()
        }.getOrElse {
            throw BookGetException(notFindBook)
        }
    }

    fun findByActive(): Collection<Book> {
        return runCatching {
            bookRepository.findByStatus(BookStatus.ATIVO)
        }.getOrElse {
            throw BookGetException(notFindBook)
        }
    }

    fun findById(id: Int): Book {
        return runCatching {
            bookRepository.findBookById(id)!!
        }.getOrElse { throw BookGetException(notFindBook) }
    }

    fun delete(id: Int) {
        runCatching {
            bookRepository.findBookById(id).let {
                bookRepository.save(it!!.copy(status = BookStatus.CANCELADO))
            }
        }.getOrElse { throw BookDeleteException(notFindBook) }
    }

    fun update(id: Int, book: UpdateBookRequest): Book {
        return runCatching {
            this.findById(id).let {
                bookRepository.save(
                    it.copy(
                        name = book.name ?: it.name,
                        image = book.image ?: it.image,
                        price = book.price ?: it.price,
                        updatedAt = Instant.now()
                    )
                )
            }
        }.getOrElse {
            throw BookUpdateException("Unexpected error in update book " + it.message)
        }
    }

    fun enable(identifier: Int): Book {
        return runCatching {
            bookRepository.findBookById(identifier)!!
                .let {
                    bookRepository.save(it.copy(status = BookStatus.ATIVO))
                }
        }.getOrElse { throw BookEnabledException("Could not enable book " + it.message) }
    }

    fun deleteByCustomer(customer: Customer) {
        runCatching {
            val books = bookRepository.findByCustomer(customer)
            books.map {
                book ->
                bookRepository.save(book.copy(status = BookStatus.DELETADO))
            }
        }.getOrElse { throw BookDeleteException(notFindAssociateCustomer) }
    }
}
