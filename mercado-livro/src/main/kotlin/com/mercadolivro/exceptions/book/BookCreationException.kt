package com.mercadolivro.exceptions.book

import com.mercadolivro.common.exceptions.CreationException

class BookCreationException (message: String?, cause: Throwable? = null) : CreationException(message, cause)
