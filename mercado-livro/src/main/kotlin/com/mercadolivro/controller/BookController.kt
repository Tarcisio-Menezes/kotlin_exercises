package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
class BookController(
    val customerService: CustomerService,
    val bookService : BookService
) {

    @PostMapping
    fun create(@RequestBody book: PostBookRequest) {
        val customer = customerService.getCustomerById(book.customerId)
        bookService.create(book.toBookModel(customer))
    }
}