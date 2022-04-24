package com.mercadolivro.service

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?): List<Customer> {
        runCatching {
            name?.let {
                return customerRepository.findByNameContaining(it)
            }
            return customerRepository.findAll().toList()
        }.getOrElse { throw Exception() }
    }

    fun findByActive(): List<Customer> {
        runCatching {
            return customerRepository.findByStatus(CustomerStatus.ENABLED)
        }.getOrElse { throw Exception() }
    }

    fun getCustomerById(id: Int): Customer? {
        runCatching {
            return customerRepository.findCustomerById(id)
        }.getOrElse { throw Exception() }
    }

    fun update(id: Int, customer: UpdateCustomerRequest): Customer? {
        return runCatching {
            getCustomerById(id).apply {
                customerRepository.save(
                    this!!.copy(
                        name = customer.name,
                        email = customer.email
                    )
                )
            }
            }.getOrElse { throw Exception() }
    }

    fun create(customer: CreateCustomerRequest): Customer {
        return runCatching {
            customerRepository.save(customer.toCustomerModel())
        }.getOrElse { throw Exception() }
    }

    fun delete(id: Int) {
        runCatching {
            getCustomerById(id).apply {
                    bookService.deleteByCustomer(this!!)
                    customerRepository.save(this.copy(status = CustomerStatus.DISABLED))
                }
            }.getOrElse { throw Exception() }
    }

    fun enable(id: Int): Customer {
        runCatching {
            val customer = getCustomerById(id).apply {
                customerRepository.save(this!!.copy(status = CustomerStatus.ENABLED))
            }
            return customer!!
        }.getOrElse { throw Exception() }
    }
}
