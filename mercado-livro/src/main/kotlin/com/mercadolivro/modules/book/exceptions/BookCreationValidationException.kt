package com.mercadolivro.modules.book.exceptions

import com.mercadolivro.common.exceptions.CreationValidationException

class BookCreationValidationException(message: String?, cause: Throwable? = null) :
    CreationValidationException(message, cause)
