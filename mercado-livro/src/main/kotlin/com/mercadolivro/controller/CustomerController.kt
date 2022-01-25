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
    fun getAll(@RequestParam name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel? {
        for (customer in customers) {
            if (customer.id == id) {
                return customer
            }
        }
        return null
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody customer: PostCustomerRequest): CustomerModel? {
//        for (index in customers) {
//            if (index.id == id) {
//                index.name = customer.name
//                index.email = customer.email
//                return CustomerModel(id, customer.name, customer.email)
//            }
//        }
//        return null

        customers.filter { it.id == id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }

        return CustomerModel(id, customer.name, customer.email)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest): PostCustomerRequest {

        var id: Int = if (customers.isEmpty()) {
            1
        } else {
            customers.last().id + 1
        }

        customers.add(CustomerModel(id, customer.name, customer.email))
        return customer
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customers.removeIf { it.id == id }
    }

}