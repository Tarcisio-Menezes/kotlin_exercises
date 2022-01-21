package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController {

    @GetMapping
    fun getCustomer(): CustomerModel {
        return CustomerModel(1, "Tarcísio Menezes", "tarcisio@email.com")
    }

    @PostMapping
    fun create(@RequestBody customer: PostCustomerRequest): PostCustomerRequest {
        return customer
    }
}