package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun getCustomer(id: Int): CustomerModel? {
        for (customer in customers) {
            if (customer.id == id) {
                return customer
            }
        }
        return null
    }

    fun update(id: Int, customer: PostCustomerRequest): CustomerModel? {
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

    fun create(customer: PostCustomerRequest): PostCustomerRequest {
        val id: Int = if (customers.isEmpty()) {
            1
        } else {
            customers.last().id + 1
        }

        customers.add(CustomerModel(id, customer.name, customer.email))
        return customer
    }

    fun delete(id: Int) {
        customers.removeIf { it.id == id }
    }
}