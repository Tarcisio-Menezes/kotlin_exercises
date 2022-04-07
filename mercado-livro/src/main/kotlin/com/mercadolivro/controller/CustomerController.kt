package com.mercadolivro.controller

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.model.Customer
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("customer")
class CustomerController (
    val customerService: CustomerService
        ) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<Customer> {
        return customerService.getAll(name)
    }

    @GetMapping("/{identifier}")
    fun getCustomer(@PathVariable id: UUID): Customer? {
        return customerService.getCustomerByIdentifier(id)
    }

    @GetMapping("/active")
    fun findByActive(): List<Customer> {
        return customerService.findByActive()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody customer: UpdateCustomerRequest): Customer? {
        customerService.update(id, customer.toCustomerModel())
        return Customer(id, customer.name, customer.email)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: CreateCustomerRequest): CreateCustomerRequest {
        customerService.create(customer.toCustomerModel())
        return CreateCustomerRequest(customer.name, customer.email)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        return customerService.delete(id)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun enable(@PathVariable id: Int): Customer {
        return customerService.enable(id)
    }
}