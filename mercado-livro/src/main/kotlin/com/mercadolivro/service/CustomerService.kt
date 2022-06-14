package com.mercadolivro.service

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.entitys.Customer
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.exceptions.customer.*
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    private val notFindingAuthor = "No author corresponding search!"

    fun getAll(name: String?): Collection<Customer> {
        return runCatching {
            name?.let {
                customerRepository.findByNameContaining(it)
            } ?: customerRepository.findAll().toList()
        }.getOrElse { throw CustomerGetException(notFindingAuthor) }
    }

    fun findByActive(): List<Customer> {
        return runCatching {
            customerRepository.findByStatus(CustomerStatus.ENABLED)
        }.getOrElse { throw CustomerFindByActiveException(notFindingAuthor) }
    }

    fun getCustomerById(id: Int): Customer {
        return runCatching {
            customerRepository.findCustomerById(id)!!
        }.getOrElse { throw CustomerGetByIdException(notFindingAuthor) }
    }

    fun update(id: Int, customer: UpdateCustomerRequest): Customer {
        runCatching {
            return when (val customerById = customerRepository.findCustomerById(id)) {
                null -> throw CustomerUpdateException("$notFindingAuthor. Not could be updated!")
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
        }.getOrElse { throw CustomerUpdateException("Error in updating process! Try again! " + it.message) }
    }

    fun create(customer: CreateCustomerRequest): Customer {
        runCatching {
            return when (customerRepository.findByEmail(customer.email)) {
                null -> {
                    customerRepository.save(customer.toCustomerModel())
                }
                else -> throw CustomerCreationValidationException("Author already registered")
            }
        }.getOrElse { throw it }
    }

    fun delete(id: Int) {
        runCatching {
            when (val customer = customerRepository.findCustomerById(id)) {
                null -> throw CustomerDeleteException("$notFindingAuthor. Could not be deleted!")
                else -> {
                    customer.apply {
                        bookService.deleteByCustomer(this)
                        customerRepository.save(this.copy(status = CustomerStatus.DISABLED))
                    }
                }
            }
        }.getOrElse { throw CustomerDeleteException("Error in deleted process Author. Detail: " + it.message) }
    }

    fun enable(id: Int): Customer {
        runCatching {
            return when (val customer = customerRepository.findCustomerById(id)) {
                null -> throw CustomerEnableException("$notFindingAuthor. Could not be enabled!")
                else -> customer.apply {
                    customerRepository.save(this.copy(status = CustomerStatus.ENABLED))
                }
            }
        }.getOrElse { throw CustomerEnableException("Error in enabled process Author. Detail: " + it.message) }
    }
}
