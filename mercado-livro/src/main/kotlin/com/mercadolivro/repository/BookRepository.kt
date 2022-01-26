package com.mercadolivro.repository

import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int> {

    fun findByNameContaining(name: String): List<BookModel>
}