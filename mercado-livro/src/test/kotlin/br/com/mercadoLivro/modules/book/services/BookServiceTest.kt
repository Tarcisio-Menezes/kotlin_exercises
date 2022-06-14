package br.com.mercadoLivro.modules.book.services

import br.com.mercadoLivro.UnitTests
import br.com.mercadoLivro.mock.BookMock
import br.com.mercadoLivro.mock.CustomerMock
import com.github.tomakehurst.wiremock.common.Strings
import com.mercadolivro.controller.request.CreateBookRequest
import com.mercadolivro.controller.request.UpdateBookRequest
import com.mercadolivro.entitys.Book
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.exceptions.book.BookCreationException
import com.mercadolivro.exceptions.book.BookDeleteException
import com.mercadolivro.exceptions.book.BookEnabledException
import com.mercadolivro.exceptions.book.BookGetException
import com.mercadolivro.exceptions.book.BookUpdateException
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.service.BookService
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
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

internal class BookServiceTest(
    @MockK private val bookRepository: BookRepository,
    @MockK private val customerRepository: CustomerRepository,
) : UnitTests() {

    private val bookService = BookService(bookRepository, customerRepository)
    private val validBook = BookMock.validBook()
    private val invalidBook = BookMock.invalidBook()
    private val validCustomer = CustomerMock.validCustomer()

    private val request = CreateBookRequest(
        name = "Como não pagar o aluguel",
        price = 12.90,
        image = "https://imagens3.ne10.uol.com.br/blogsne10/social1/uploads/2021/05/Ramon-Valdes-como-Seu-Madruga-em-Chaves.jpg",
        customerId = 1
    )
    private val updateRequest = UpdateBookRequest(
        name = "Como não pagar o aluguel",
        price = 12.90,
        image = "https://imagens3.ne10.uol.com.br/blogsne10/social1/uploads/2021/05/Ramon-Valdes-como-Seu-Madruga-em-Chaves.jpg"
    )

    @Nested
    inner class CreateBook {

        @Test
        internal fun `should create a new book`() {
            every { customerRepository.findCustomerById(any()) } returns validCustomer
            every { bookRepository.save(any()) } returns validBook

            val result = bookService.create(request)

            assertThat(result).isNotNull.usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "image", "customerId")
                .isEqualTo(validBook)
        }

        @Test
        internal fun `should throw exception when not find a customer`() {
            every { customerRepository.findCustomerById(any()) } returns null

            assertThrows<BookCreationException> {
                bookService.create(request)
            }

            verify(inverse = true) { bookRepository.save(any())  }
            confirmVerified()
        }
    }

    @Nested
    inner class FindBooks {

        @ParameterizedTest
        @ValueSource(strings = ["aluguel", "pagar"])
        internal fun `should find all books using name param`(name: String) {
            every { bookRepository.findByNameContaining(name) } returns listOf(validBook)

            val result = bookService.findAll(name)

            assertEquals(1, result.size)
            verify(inverse = true) { bookRepository.findAll() }
            confirmVerified()
        }

        @Test
        internal fun `should find all books`() {
            every { bookRepository.findAll() } returns listOf(validBook)

            val result = bookService.findAll(null)

            assertEquals(1, result.size)
            verify(inverse = true) { bookRepository.findByNameContaining(any()) }
            confirmVerified()
        }

        @ParameterizedTest
        @ValueSource(strings = ["zica", "virus"])
        internal fun `should throw exception when could not find name`(name: String) {
            every { bookRepository.findByNameContaining(name) } throws Exception()

            assertThrows<BookGetException> {
                bookService.findAll(name)
            }

            verify(exactly = 1) { bookRepository.findByNameContaining(any()) }
            verify(inverse = true) { bookRepository.findAll() }
            confirmVerified()
        }

        @Test
        internal fun `should find active books`() {
            every { bookRepository.findByStatus(any()) } returns listOf(validBook)

            val result = bookService.findByActive()

            assertEquals(1, result.size)
        }

        @Test
        internal fun `should throw exception when not find by active books`() {
            every { bookRepository.findByStatus(any()) } throws Exception()

            assertThrows<BookGetException> {
                bookService.findByActive()
            }
        }

        @Test
        internal fun `should find book by id`() {
            every { bookRepository.findBookById(1) } returns validBook

            val result = bookService.findById(1)

            assertThat(result).isNotNull.usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "image", "customerId")
                .isEqualTo(validBook)
        }

        @Test
        internal fun `should throw exception when not find by id books`() {
            every { bookRepository.findBookById(1) } returns null

            assertThrows<BookGetException> {
                bookService.findById(1)
            }
        }
    }

    @Nested
    inner class DeleteBook {

        @Test
        internal fun `should disable book by id`() {
            every { bookRepository.findBookById(1) } returns validBook
            every { bookRepository.save(any()) } returns invalidBook

            val result = bookService.delete(1)

            assertThat(result).isNotNull.usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "image", "customerId", "status")
                .isEqualTo(invalidBook)
        }

        @Test
        internal fun `should throw exception when not find book by id`() {
            every { bookRepository.findBookById(1) } returns null

            assertThrows<BookDeleteException> {
                bookService.delete(1)
            }

            verify(inverse = true) { bookRepository.save(any()) }
            confirmVerified()
        }

        @Test
        internal fun `should delete book by customer`() {
            every { bookRepository.findByCustomer(any()) } returns listOf(validBook)
            every { bookRepository.save(any()) } returns invalidBook

            val result = bookService.deleteByCustomer(validCustomer)

            assertThat(result).isNotNull.usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "image", "customerId", "status")
                .isEqualTo(invalidBook)
        }

        @Test
        internal fun `should throw exception when not find book by id in delete by customer`() {
            every { bookRepository.findByCustomer(any()) } throws Exception()

            assertThrows<BookDeleteException> {
                bookService.deleteByCustomer(validCustomer)
            }

            verify(inverse = true) { bookRepository.save(any()) }
            confirmVerified()
        }
    }

    @Nested
    inner class EnableBook {

        @Test
        internal fun `should enable book by id`() {
            every { bookRepository.findBookById(1) } returns invalidBook
            every { bookRepository.save(any()) } returns validBook

            val result = bookService.enable(1)

            assertThat(result).isNotNull.usingRecursiveComparison()
                .comparingOnlyFields("image", "customerId", "status")
                .isEqualTo(validBook)
        }

        @Test
        internal fun `should throw exception to not find book`() {
            every { bookRepository.findBookById(any()) } returns null

            assertThrows<BookEnabledException> {
                bookService.enable(1)
            }

            verify(inverse = true) { bookRepository.save(any()) }
            confirmVerified()
        }
    }

    @Nested
    inner class UpdateBook {

        @Test
        internal fun `should update a book`() {
            val alterValidBook = validBook.copy(image = "xx")
            every { bookService.findById(any()) } returns validBook
            every { bookRepository.save(any()) } returns alterValidBook

            val result = bookService.update(1, updateRequest)

            assertThat(result).isNotNull.usingRecursiveComparison()
                .comparingOnlyFields("image", "customerId", "status")
                .isEqualTo(alterValidBook)
        }

        @Test
        internal fun `should throw exception when not find a book`() {
            every { bookService.findById(any()) } throws Exception()

            assertThrows<BookUpdateException> {
                bookService.update(1, updateRequest)
            }

            verify(inverse = true) { bookRepository.save(any()) }
            confirmVerified()
        }
    }
}
