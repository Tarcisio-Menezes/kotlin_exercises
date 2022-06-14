package br.com.mercadoLivro

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.annotation.Profile

@Profile("test")
@ExtendWith(MockKExtension::class)
abstract class UnitTests
