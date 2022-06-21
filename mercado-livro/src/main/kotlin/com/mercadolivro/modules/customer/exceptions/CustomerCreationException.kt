package com.mercadolivro.modules.customer.exceptions

import com.mercadolivro.common.exceptions.CreationException

class CustomerCreationException(message: String?, cause: Throwable? = null) : CreationException(message, cause)
