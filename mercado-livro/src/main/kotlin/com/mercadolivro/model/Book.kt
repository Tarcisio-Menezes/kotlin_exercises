package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.expression.spel.ast.Identifier
import java.time.Instant
import java.util.UUID
import javax.persistence.*

@Entity(name = "book")
data class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,

    @Column
    val identifier: UUID,

    @Column
    val name: String,

    @Column
    val price: Float,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = BookStatus.ATIVO,

    @Column
    val image: String,

    @CreatedDate
    var createdAt: Instant? = null,

    @LastModifiedDate
    var updatedAt: Instant? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
)