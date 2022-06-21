package com.mercadolivro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.web.config.EnableSpringDataWebSupport

@SpringBootApplication
@EnableJpaRepositories
@ConfigurationPropertiesScan
class MercadoLivroApplication

fun main(args: Array<String>) {
    runApplication<MercadoLivroApplication>(*args)
}
