package com.mercadolivro.service

import com.fasterxml.jackson.annotation.JsonAlias
import com.mercadolivro.controller.request.PostBookRequest
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
        val book: BookModel = findById(id)
        book.status = BookStatus.CANCELADO
        bookRepository.save(book)
    }
}