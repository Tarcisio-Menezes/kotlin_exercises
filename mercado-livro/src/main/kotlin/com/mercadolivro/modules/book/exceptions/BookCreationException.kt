package com.mercadolivro.modules.book.exceptions

import com.mercadolivro.common.exceptions.CreationException

class BookCreationException(message: String?, cause: Throwable? = null) : CreationException(message, cause)
