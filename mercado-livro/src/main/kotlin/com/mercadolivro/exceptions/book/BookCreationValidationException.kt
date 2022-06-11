package com.mercadolivro.exceptions.book

import com.mercadolivro.common.exceptions.CreationValidationException

class BookCreationValidationException (message: String?, cause: Throwable? = null) :
    CreationValidationException(message, cause)
