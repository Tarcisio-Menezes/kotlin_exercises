package com.mercadolivro.modules.customer.dataproviders.jpa.entities

import com.mercadolivro.modules.customer.enums.CustomerStatus
import com.mercadolivro.modules.customer.enums.CustomerStatus.ENABLED
import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Enumerated
import javax.persistence.EnumType

@Entity(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var email: String,
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus = ENABLED,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Customer

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
