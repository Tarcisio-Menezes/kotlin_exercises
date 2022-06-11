package com.mercadolivro.entitys

import com.mercadolivro.enums.BookStatus
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
    @Column
    val name: String,
    @Column
    val price: Double,
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
