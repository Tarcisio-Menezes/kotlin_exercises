package com.mercadolivro.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customer")
class CustomerController {

    @GetMapping
    fun helloWorld(): String {
        return "Fala meu parceiro!"
    }

    @GetMapping("2")
    fun customer2(): String {
        return "Fala meu parceiro, esta é a rota 2, dentro de /custumer"
    }
}