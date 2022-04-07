package com.mercadolivro.service

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?): List<Customer> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun findByActive(): List<Customer> {
        return customerRepository.findByStatus(CustomerStatus.ENABLED)
    }

    fun getCustomerByIdentifier(identifier: UUID): Customer {
        kotlin.runCatching {
            return customerRepository.findByIdentifier(identifier)!!
        }.getOrElse { throw Exception() }
    }

    fun update(id: Int, customer: UpdateCustomerRequest): Customer? {
        if (customerRepository.existsById(id)) {
            val newCustomer = Customer(id, customer.name, customer.email)
            customerRepository.save(newCustomer)
            return newCustomer
        }

        else {
            throw Exception()
        }
    }

    fun create(customer: Customer): CreateCustomerRequest {
        customerRepository.save(customer)
        return CreateCustomerRequest(name = customer.name, email = customer.email)
    }

    fun delete(id: Int) {
        if (customerRepository.existsById(id)) {
            kotlin.runCatching {
                getCustomerByIdentifierentifier(id).apply {
                    bookService.deleteByCustomer(this)
                    customerRepository.save(this.copy(status = CustomerStatus.DISABLED))
                }
            }.getOrElse { throw Exception() }
        }

        else { throw Exception() }
    }

    fun enable(id: Int): Customer {
        kotlin.runCatching {
            val customer = getCustomerByIdentifier(id).apply {
                customerRepository.save(this.copy(status = CustomerStatus.ENABLED))
            }
            return customer
        }.getOrElse { throw Exception() }
    }
}
