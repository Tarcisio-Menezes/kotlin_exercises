package com.mercadolivro.common.exceptions.handler

import com.mercadolivro.common.exceptions.CreationException
import com.mercadolivro.common.exceptions.CreationValidationException
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

    @ExceptionHandler(CreationException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCreationException(exception: CreationException) = createResponseError(exception)

    @ExceptionHandler(CreationValidationException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleCreationValidationException(exception: CreationValidationException) = createResponseError(exception)

    private fun createResponseError(exception: RuntimeException): List<ErrorDetail> {
        return listOf(ErrorDetail(exception.message!!, exception.message!!))
    }
}
