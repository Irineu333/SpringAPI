package com.neo.api.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import java.io.Serializable
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "tb_payment")
data class Payment(
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val moment: Instant? = null,
    @OneToOne
    @MapsId
    @JsonIgnore val order: Order
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as Payment
        return id != null && id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}