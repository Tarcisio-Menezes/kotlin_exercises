package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(name)
        }
        return customerRepository.findAll().toList()
    }

    fun getCustomerById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun update(id: Int, customer: PutCustomerRequest): CustomerModel? {
        if (customerRepository.existsById(id)) {
            val newCustomer: CustomerModel = CustomerModel(id, customer.name, customer.email)
            customerRepository.save(newCustomer)
            return newCustomer
        }

        else {
            throw Exception()
        }
    }

    fun create(customer: CustomerModel): PostCustomerRequest {
        customerRepository.save(customer)
        return PostCustomerRequest(name = customer.name, email = customer.email)
    }

    fun delete(id: Int) {
        if (customerRepository.existsById(id)) {
            kotlin.runCatching {
                getCustomerById(id).apply { bookService.deleteByCustomer(this) }
                return customerRepository.deleteById(id)
            }
        }

        throw Exception()
    }
}
