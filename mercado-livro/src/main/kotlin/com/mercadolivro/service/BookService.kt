package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun create(book: BookModel): BookModel {
        bookRepository.save(book)
        return BookModel(id = book.id, name = book.name, price = book.price, book.status, customer = book.customer)
    }

    fun findAll(name: String?): List<BookModel> {
        name?.let {
            return bookRepository.findByNameContaining(name)
        }
        return bookRepository.findAll().toList()
    }

    fun findByActive(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun delete(id: Int) {
        return findById(id).let {
            book ->
            bookRepository.save(book.copy(status = BookStatus.CANCELADO))
        }
    }

    fun update(book: BookModel): BookModel {
        bookRepository.save(book)
        return book
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        books.map {
            book -> bookRepository.save(book.copy(status = BookStatus.DELETADO))
        }
    }
}