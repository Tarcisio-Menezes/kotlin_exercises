package com.mercadolivro.service

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.exceptions.customer.*
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
        }.getOrElse { throw CustomerGetException(message = "No author corresponding search!") }
    }

    fun findByActive(): List<Customer> {
        runCatching {
            return customerRepository.findByStatus(CustomerStatus.ENABLED)
        }.getOrElse { throw CustomerFindByActiveException(message = "Error in finding process! Try again!") }
    }

    fun getCustomerById(id: Int): Customer? {
        runCatching {
            return customerRepository.findCustomerById(id)
        }.getOrElse { throw CustomerGetByIdException(message = "Error in finding by id! Try again!") }
    }

    fun update(id: Int, customer: UpdateCustomerRequest): Customer? {
        runCatching {
            return when(val customerById = getCustomerById(id)) {
                null -> throw CustomerUpdateException(message = "Author not found. Not could be updated!")
                else -> {
                    customerById.apply {
                        customerRepository.save(
                            this.copy(
                                name = customer.name,
                                email = customer.email
                            )
                        )
                    }
                }
            }
        }.getOrElse { throw CustomerUpdateException(message = "Error in updating process! Try again!") }
    }

    fun create(customer: CreateCustomerRequest): Customer {
        runCatching {
            return when(customerRepository.findByEmail(customer.email)) {
                null -> {
                    customerRepository.save(customer.toCustomerModel())
                }
                else -> throw CustomerCreationValidationException(message = "\n" +
                        "Author already registered")
            }

        }.getOrElse { throw CustomerCreationException(message = "Error in creation process Author. Detail:" + it.message, it) }
    }

    fun delete(id: Int) {
        runCatching {
            when(val customer = getCustomerById(id)) {
                null -> throw CustomerDeleteException(message = "Author not found. Could not be deleted!")
                else -> {
                    customer.apply {
                        bookService.deleteByCustomer(this)
                        customerRepository.save(this.copy(status = CustomerStatus.DISABLED))
                    }
                }
            }
        }.getOrElse { throw CustomerDeleteException(message = "Error in deleted process Author. Detail:" + it.message) }
    }

    fun enable(id: Int): Customer {
        runCatching {
            return when(val customer = getCustomerById(id)) {
                null -> throw CustomerEnableException(message = "Author not found. Could not be enabled!")
                else -> customer.apply {
                customerRepository.save(this.copy(status = CustomerStatus.ENABLED))
                }
            }
        }.getOrElse { throw CustomerEnableException(message = "Error in enabled process Author. Detail:" + it.message) }
    }
}
