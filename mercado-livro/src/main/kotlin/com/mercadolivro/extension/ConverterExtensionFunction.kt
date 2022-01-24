package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): PostCustomerRequest {
    return PostCustomerRequest(name = this.name, email = this.email)
}