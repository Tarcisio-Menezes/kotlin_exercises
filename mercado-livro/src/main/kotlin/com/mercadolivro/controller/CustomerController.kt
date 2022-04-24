package com.mercadolivro.controller

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.controller.response.CreateCustomerResponse
import com.mercadolivro.controller.response.UpdateCustomerResponse
import com.mercadolivro.controller.response.toAPIResponse
import com.mercadolivro.controller.response.toUpdateAPIResponse
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

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): Customer? {
        return customerService.getCustomerById(id)
    }

    @GetMapping("/active")
    fun findByActive(): List<Customer> {
        return customerService.findByActive()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody customer: UpdateCustomerRequest): UpdateCustomerResponse {
        return customerService.update(id, customer)!!.toUpdateAPIResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: CreateCustomerRequest): CreateCustomerResponse {
        return customerService.create(customer).toAPIResponse()
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
