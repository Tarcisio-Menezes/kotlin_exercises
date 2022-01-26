package com.mercadolivro.service

import com.fasterxml.jackson.annotation.JsonAlias
import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.model.BookModel
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun create(book: BookModel): BookModel {
        bookRepository.save(book)
        return BookModel(name = book.name, price = book.price, customer = book.customer)
    }
}