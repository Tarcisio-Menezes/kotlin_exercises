package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RestController
@RequestMapping("customer")
class CustomerController {

    val customers = mutableListOf<CustomerModel>()

    @GetMapping
    fun getAllCustomer(): List<CustomerModel> {
        return customers
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel? {
        for (customer in customers) {
            if(customer.id == id) {
                return customer
            }
        }
        return null
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest): PostCustomerRequest {

        var id: Int = if(customers.isEmpty()) {
            1
        } else {
            customers.last().id + 1
        }

        customers.add(CustomerModel(id, customer.name, customer.email))
        return customer
    }
}