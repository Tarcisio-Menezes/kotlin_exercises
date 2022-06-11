package com.mercadolivro.service

import com.mercadolivro.controller.request.CreateBookRequest
import com.mercadolivro.controller.request.UpdateBookRequest
import com.mercadolivro.controller.response.toGetAPIResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.entitys.Book
import com.mercadolivro.entitys.Customer
import com.mercadolivro.exceptions.book.BookCreationException
import com.mercadolivro.exceptions.book.BookDeleteException
import com.mercadolivro.exceptions.book.BookEnabledException
import com.mercadolivro.exceptions.book.BookGetException
import com.mercadolivro.exceptions.book.BookUpdateException
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.repository.CustomerRepository
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
        runCatching {
            name?.let {
                bookRepository.findByNameContaining(name)
            }
            return bookRepository.findAll().toList()
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
        return runCatching{
            this.findById(id).apply {
                bookRepository.save(
                    this.copy(
                        name = book.name ?: this.name,
                        image = book.image ?: this.image,
                        price = book.price ?: this.price,
                        updatedAt = Instant.now()
                    ))
            }
        }.getOrElse {
            throw BookUpdateException("Unexpected error in update book " + it.message)
        }
    }

    fun enable(identifier: Int): Book {
        return runCatching {
            bookRepository.findBookById(identifier)!!
                .apply {
                bookRepository.save(this.copy(status = BookStatus.ATIVO))
            }
        }.getOrElse { throw BookEnabledException("Could not enable book " + it.message) }
    }

    fun deleteByCustomer(customer: Customer) {
        runCatching {
            val books = bookRepository.findByCustomer(customer)
            books.map {
                    book -> bookRepository.save(book.copy(status = BookStatus.DELETADO))
            }
        }.getOrElse { throw BookDeleteException(notFindAssociateCustomer) }
    }
}
