package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): PostCustomerRequest {
    return PostCustomerRequest(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(): PutCustomerRequest {
    return PutCustomerRequest(name = this.name, email = this.email)
}