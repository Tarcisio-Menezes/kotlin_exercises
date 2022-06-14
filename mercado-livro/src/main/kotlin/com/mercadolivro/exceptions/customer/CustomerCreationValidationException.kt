package com.mercadolivro.exceptions.customer

import com.mercadolivro.common.exceptions.CreationValidationException

class CustomerCreationValidationException(message: String?, cause: Throwable? = null) :
    CreationValidationException(message, cause)
