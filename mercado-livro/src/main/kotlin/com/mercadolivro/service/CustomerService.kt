package com.mercadolivro.service

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.controller.request.toCustomerModel
import com.mercadolivro.controller.response.CreateCustomerResponse
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

    fun getCustomerByIdentifier(identifier: UUID): Customer {
        runCatching {
            return customerRepository.findByIdentifier(identifier)!!
        }.getOrElse { throw Exception() }
    }

    fun update(id: UUID, customer: UpdateCustomerRequest): Customer? {
        return runCatching {
            getCustomerByIdentifier(id).apply {
                customerRepository.save(
                    this.copy(
                        name = customer.name,
                        email = customer.email
                    )
                )
            }
            }.getOrElse { throw Exception() }
    }

    fun create(customer: Customer): CreateCustomerResponse {
        return runCatching {
            customerRepository.save(customer.toCustomerModel()!!)
        }.getOrElse { throw Exception() }
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
