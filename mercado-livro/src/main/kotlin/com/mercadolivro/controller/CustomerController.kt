package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RestController
@RequestMapping("customer")
class CustomerController (
    val customerService: CustomerService
        ) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerModel> {
        return customerService.getAll(name)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel? {
        return customerService.getCustomer(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest): CustomerModel? {
        return customerService.update(id, customer.toCustomerModel())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest): PostCustomerRequest {
        customerService.create(customer.toCustomerModel())
        return PostCustomerRequest(customer.name, customer.email)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        return customerService.delete(id)
    }

}