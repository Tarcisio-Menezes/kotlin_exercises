package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.model.BookModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    val customerService: CustomerService,
    val bookService : BookService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody book: PostBookRequest): BookModel {
        val customer = customerService.getCustomerById(book.customerId)
        return bookService.create(book.toBookModel(customer))
    }

    @GetMapping
    fun findAll(@RequestParam name: String?): List<BookModel> {
        return bookService.findAll(name)
    }
}