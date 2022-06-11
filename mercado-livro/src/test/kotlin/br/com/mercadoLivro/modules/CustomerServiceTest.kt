package br.com.mercadoLivro.modules

import br.com.mercadoLivro.UnitTests
import br.com.mercadoLivro.mock.CustomerMock
import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.exceptions.customer.CustomerCreationValidationException
import com.mercadolivro.exceptions.customer.CustomerDeleteException
import com.mercadolivro.exceptions.customer.CustomerEnableException
import com.mercadolivro.exceptions.customer.CustomerFindByActiveException
import com.mercadolivro.exceptions.customer.CustomerGetByIdException
import com.mercadolivro.exceptions.customer.CustomerGetException
import com.mercadolivro.exceptions.customer.CustomerUpdateException
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.justRun
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

internal class CustomerServiceTest(
    @MockK private val customerRepository: CustomerRepository,
    @MockK private val bookService: BookService
) : UnitTests() {

    private val customerService = CustomerService(customerRepository, bookService)
    private val request = CreateCustomerRequest(
        name = "Ramón Valdez",
        email = "madruguinha@fakemail.com"
    )
    private val validCustomer = CustomerMock.validCustomer()
    private val invalidCustomer = CustomerMock.invalidCustomer()
    private val updateRequest = UpdateCustomerRequest(
        name = "Ramon Valdez",
        email = "madruga@vilamail.com.mx"
    )

    @Nested
    inner class CreateCustomer {

        @Test
        internal fun `should create a new customer`() {
            every { customerRepository.save(any()) } returns validCustomer
            every { customerRepository.findByEmail(any()) } returns null

            val result = customerService.create(request)

            assertThat(result).isNotNull.usingRecursiveComparison()
                .comparingOnlyFields("name", "email")
                .isEqualTo(validCustomer)
        }

        @Test
        internal fun `should throw exception with duplicated customer e-mail`() {
            every { customerRepository.findByEmail(any()) } returns validCustomer

            assertThrows<CustomerCreationValidationException> {
                customerService.create(request)
            }

            verify(inverse = true) { customerRepository.save(any()) }
            confirmVerified()
        }

        @Test
        internal fun `should throw exception with error in save customer`() {
            every { customerRepository.findByEmail(any()) } returns null
            every { customerRepository.save(any()) } throws CustomerCreationValidationException("")

            assertThrows<CustomerCreationValidationException> {
                customerService.create(request)
            }

            verify { customerRepository.save(any()) }
            confirmVerified()
        }
    }

    @Nested
    inner class GetCustomers {

        @ParameterizedTest
        @ValueSource(strings = ["ra", "dez"])
        internal fun `should get customers by name`(name: String) {

            every { customerRepository.findByNameContaining(any()) } returns listOf(CustomerMock.validCustomer())
            every { customerRepository.findAll() } returns listOf(CustomerMock.validCustomer())

            val result = customerService.getAll(name)

            assertEquals(1, result.size)
        }

        @Test
        internal fun `should throw exception could not find customer by name`() {
            every { customerRepository.findByNameContaining(any()) } throws Exception()

            assertThrows<CustomerGetException> {
                customerService.getAll("ramon")
            }
        }

        @Test
        internal fun `should get customers`() {

            every { customerRepository.findAll() } returns listOf(CustomerMock.validCustomer())

            val result = customerService.getAll(null)

            assertEquals(1, result.size)
        }

        @Test
        internal fun `should get active customers`() {
            every { customerRepository.findByStatus(any()) } returns listOf(CustomerMock.validCustomer())

            val result = customerService.findByActive()

            assertEquals(1, result.size)
        }

        @Test
        internal fun `should throw exception when not find active customers`() {
            every { customerRepository.findByStatus(any()) } throws Exception()

            assertThrows<CustomerFindByActiveException> {
                customerService.findByActive()
            }
        }

        @Test
        internal fun `should get customer by id`() {
            every { customerRepository.findCustomerById(any()) } returns validCustomer

            val result = customerService.getCustomerById(1)

            assertEquals(validCustomer.name, result.name)
            assertEquals(validCustomer.email, result.email)

            verify { customerRepository.findCustomerById(any()) }
            confirmVerified()
        }

        @Test
        internal fun `should throw exception when not get customer by id`() {
            every { customerRepository.findCustomerById(any()) } returns null

            assertThrows<CustomerGetByIdException> {
                customerService.getCustomerById(1)
            }

            verify { customerRepository.findCustomerById(any()) }
            confirmVerified()
        }
    }

    @Nested
    inner class UpdateCustomers {

        @Test
        internal fun `should update a customer`() {
            every { customerRepository.findCustomerById(any()) } returns validCustomer
            every { customerRepository.save(any()) } returns validCustomer


            val result = customerService.update(1, updateRequest)

            assertEquals(validCustomer.name, result.name)
            assertEquals(validCustomer.email, result.email)

            verify { customerRepository.findCustomerById(any()) }
            verify { customerRepository.save(any()) }
            confirmVerified()
        }

        @Test
        internal fun `should throw exception when not find customer to update`() {
            every { customerRepository.findCustomerById(any()) } returns null

            assertThrows<CustomerUpdateException> {
                customerService.update(1, updateRequest)
            }

            verify { customerRepository.findCustomerById(any()) }
            verify(inverse = true) { customerRepository.save(any()) }
            confirmVerified()
        }

        @Test
        internal fun `should throw exception when not update a customer`() {
            every { customerRepository.findCustomerById(any()) } returns validCustomer
            every { customerRepository.save(any()) } throws Exception()

            assertThrows<CustomerUpdateException> {
                customerService.update(1, updateRequest)
            }

            verify { customerRepository.findCustomerById(any()) }
            verify { customerRepository.save(any()) }
            confirmVerified()
        }
    }

    @Nested
    inner class DeleteCustomer {

        @Test
        internal fun `should disable customer`() {
            every { customerRepository.findCustomerById(any()) } returns validCustomer
            every { customerRepository.save(any()) } returns invalidCustomer
            justRun { bookService.deleteByCustomer(any()) }

            customerService.delete(1)

            verify { customerRepository.save(any()) }
            confirmVerified()
        }

        @Test
        internal fun `should throw exception when not find customer to disable`() {
            every { customerRepository.findCustomerById(any()) } returns null

             assertThrows<CustomerDeleteException> {
                 customerService.delete(1)
             }

            verify(inverse = true) { customerRepository.save(any()) }
            verify(inverse = true) { bookService.deleteByCustomer(any()) }
            confirmVerified()
        }
    }

    @Nested
    inner class EnableCustomer {

        @Test
        internal fun `should enable customer`() {
            every { customerRepository.findCustomerById(any()) } returns invalidCustomer
            every { customerRepository.save(any()) } returns validCustomer
            justRun { bookService.deleteByCustomer(any()) }

            customerService.enable(1)

            verify { customerRepository.save(any()) }
            confirmVerified()
        }

        @Test
        internal fun `should throw exception when not find customer to enable`() {
            every { customerRepository.findCustomerById(any()) } returns null

            assertThrows<CustomerEnableException> {
                customerService.enable(1)
            }

            verify(inverse = true) { customerRepository.save(any()) }
            confirmVerified()
        }

        @Test
        internal fun `should throw exception when could not to enable`() {
            every { customerRepository.findCustomerById(any()) } returns validCustomer
            every { customerRepository.save(any()) } throws CustomerEnableException("")

            assertThrows<CustomerEnableException> {
                customerService.enable(1)
            }

            verify { customerRepository.save(any()) }
            confirmVerified()
        }
    }
}