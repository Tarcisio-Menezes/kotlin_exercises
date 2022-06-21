package com.mercadolivro.common.exceptions.handler

import com.mercadolivro.common.exceptions.CreationException
import com.mercadolivro.common.exceptions.CreationValidationException
import com.mercadolivro.modules.book.exceptions.BookDeleteException
import com.mercadolivro.modules.book.exceptions.BookEnabledException
import com.mercadolivro.modules.book.exceptions.BookGetException
import com.mercadolivro.modules.book.exceptions.BookUpdateException
import com.mercadolivro.modules.customer.exceptions.CustomerDeleteException
import com.mercadolivro.modules.customer.exceptions.CustomerEnableException
import com.mercadolivro.modules.customer.exceptions.CustomerFindByActiveException
import com.mercadolivro.modules.customer.exceptions.CustomerGetByIdException
import com.mercadolivro.modules.customer.exceptions.CustomerGetException
import com.mercadolivro.modules.customer.exceptions.CustomerUpdateException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleWebExchangeBindException(
        bindException: WebExchangeBindException
    ): HttpStatus {
        throw object : WebExchangeBindException(bindException.methodParameter!!, bindException.bindingResult) {
            override val message = "${fieldError?.field} has invalid value '${fieldError?.rejectedValue}'"
        }
    }

    private fun createResponseError(exception: RuntimeException): List<ErrorDetail> {
        return listOf(ErrorDetail(exception.message!!, exception.message!!))
    }

    @ExceptionHandler(CreationException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCreationException(exception: CreationException) = createResponseError(exception)

    @ExceptionHandler(CreationValidationException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleCreationValidationException(exception: CreationValidationException) = createResponseError(exception)

    @ExceptionHandler(CustomerDeleteException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCompanyGetException(exception: CustomerDeleteException) = createResponseError(exception)

    @ExceptionHandler(CustomerEnableException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCompanyGetException(exception: CustomerEnableException) = createResponseError(exception)

    @ExceptionHandler(CustomerFindByActiveException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleCompanyGetException(exception: CustomerFindByActiveException) = createResponseError(exception)

    @ExceptionHandler(CustomerGetByIdException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleCompanyGetException(exception: CustomerGetByIdException) = createResponseError(exception)

    @ExceptionHandler(CustomerGetException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleCompanyGetException(exception: CustomerGetException) = createResponseError(exception)

    @ExceptionHandler(CustomerUpdateException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCompanyGetException(exception: CustomerUpdateException) = createResponseError(exception)

    @ExceptionHandler(BookDeleteException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCompanyGetException(exception: BookDeleteException) = createResponseError(exception)

    @ExceptionHandler(BookEnabledException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCompanyGetException(exception: BookEnabledException) = createResponseError(exception)

    @ExceptionHandler(BookGetException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleCompanyGetException(exception: BookGetException) = createResponseError(exception)

    @ExceptionHandler(BookUpdateException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCompanyGetException(exception: BookUpdateException) = createResponseError(exception)
}
