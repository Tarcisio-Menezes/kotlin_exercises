package com.mercadolivro.controller

import com.mercadolivro.controller.request.CreateBookRequest
import com.mercadolivro.controller.request.UpdateBookRequest
import com.mercadolivro.controller.response.CreateBookResponse
import com.mercadolivro.controller.response.UpdateBookResponse
import com.mercadolivro.controller.response.toCreateAPIResponse
import com.mercadolivro.controller.response.toUpdateAPIResponse
import com.mercadolivro.model.Book
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("book")
class BookController(
    val customerService: CustomerService,
    val bookService : BookService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody book: CreateBookRequest): CreateBookResponse {
        return bookService.create(book).toCreateAPIResponse()
    }

    @GetMapping
    fun findAll(@RequestParam name: String?): Collection<Book> {
        return bookService.findAll(name)
    }

    @GetMapping("/active")
    fun findByActive(): Collection<Book> {
        return bookService.findByActive()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): Book {
        return bookService.findById(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        return bookService.delete(id)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun enable(@PathVariable id: UUID): Book {
        return bookService.enable(id)!!
    }

    @PutMapping("/{identifier}")
    fun update(@PathVariable identifier: UUID, @RequestBody book: UpdateBookRequest): UpdateBookResponse {
        return bookService.update(identifier, book).toUpdateAPIResponse()
    }
}
