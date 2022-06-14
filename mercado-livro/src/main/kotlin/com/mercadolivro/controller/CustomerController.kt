package com.mercadolivro.controller

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.controller.response.CreateCustomerResponse
import com.mercadolivro.controller.response.FindCustomerResponse
import com.mercadolivro.controller.response.UpdateCustomerResponse
import com.mercadolivro.controller.response.toAPIResponse
import com.mercadolivro.controller.response.toGetResponse
import com.mercadolivro.controller.response.toUpdateAPIResponse
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController(
    val customerService: CustomerService
) {
    @GetMapping
    fun getAll(@RequestParam name: String?): Collection<FindCustomerResponse> {
        return customerService.getAll(name).toGetResponse()
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): FindCustomerResponse {
        return customerService.getCustomerById(id).toGetResponse()
    }

    @GetMapping("/active")
    fun findByActive(): Collection<FindCustomerResponse> {
        return customerService.findByActive().toGetResponse()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody customer: UpdateCustomerRequest): UpdateCustomerResponse {
        return customerService.update(id, customer).toUpdateAPIResponse()
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
    fun enable(@PathVariable id: Int): UpdateCustomerResponse {
        return customerService.enable(id).toUpdateAPIResponse()
    }
}
