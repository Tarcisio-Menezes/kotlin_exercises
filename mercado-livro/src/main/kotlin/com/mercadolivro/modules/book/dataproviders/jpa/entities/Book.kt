package com.mercadolivro.modules.book.dataproviders.jpa.entities

import com.mercadolivro.modules.customer.dataproviders.jpa.entities.Customer
import com.mercadolivro.modules.book.enums.BookStatus
import com.mercadolivro.modules.book.enums.BookStatus.ATIVO
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.*

@Entity(name = "book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val price: Double,
    @Enumerated(EnumType.STRING)
    var status: BookStatus = ATIVO,
    val image: String? = null,
    @CreatedDate
    var createdAt: Instant? = null,
    @LastModifiedDate
    var updatedAt: Instant? = null,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    var customer: Customer
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Book

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
