package br.com.mercadoLivro.mock

import com.mercadolivro.modules.book.dataproviders.jpa.entities.Book
import com.mercadolivro.modules.book.enums.BookStatus.ATIVO
import com.mercadolivro.modules.book.enums.BookStatus.CANCELADO

class BookMock {

    companion object {
        fun validBook() = Book(
            name = "Como não pagar o aluguel",
            price = 27.90,
            status = ATIVO,
            image = "https://imagens3.ne10.uol.com.br/blogsne10/social1/uploads/2021/05/Ramon-Valdes-como-Seu-Madruga-em-Chaves.jpg",
            customer = CustomerMock.validCustomer()
        )

        fun invalidBook() = Book(
            name = "Como pagar o aluguel",
            price = 27.90,
            status = CANCELADO,
            image = "https://imagens3.ne10.uol.com.br/blogsne10/social1/uploads/2021/05/Ramon-Valdes-como-Seu-Madruga-em-Chaves.jpg",
            customer = CustomerMock.validCustomer()
        )
    }
}
