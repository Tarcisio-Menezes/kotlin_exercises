package com.mercadolivro.controller

import com.mercadolivro.controller.request.CreateBookRequest
import com.mercadolivro.controller.request.UpdateBookRequest
import com.mercadolivro.controller.response.CreateBookResponse
import com.mercadolivro.controller.response.FindBookResponse
import com.mercadolivro.controller.response.UpdateBookResponse
import com.mercadolivro.controller.response.toCreateAPIResponse
import com.mercadolivro.controller.response.toGetAPIResponse
import com.mercadolivro.controller.response.toUpdateAPIResponse
import com.mercadolivro.entitys.Book
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.Collections

@RestController
@RequestMapping("book")
class BookController(
    val bookService : BookService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody book: CreateBookRequest): CreateBookResponse {
        return bookService.create(book).toCreateAPIResponse()
    }

    @GetMapping
    fun findAll(@RequestParam name: String?): Collection<FindBookResponse> {
        return bookService.findAll(name).toGetAPIResponse()
    }

    @GetMapping("/active")
    fun findByActive(): Collection<FindBookResponse> {
        return bookService.findByActive().toGetAPIResponse()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): FindBookResponse {
        return bookService.findById(id).toGetAPIResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        return bookService.delete(id)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun enable(@PathVariable id: Int): UpdateBookResponse {
        return bookService.enable(id).toUpdateAPIResponse()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody book: UpdateBookRequest): UpdateBookResponse {
        return bookService.update(id, book).toUpdateAPIResponse()
    }
}
