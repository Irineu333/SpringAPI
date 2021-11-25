package com.neo.api.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.neo.api.entities.enums.OrderStatus
import org.hibernate.Hibernate
import java.io.Serializable
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "tb_order")
data class Order(
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    private val orderStatus: OrderStatus? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(nullable = false)
    val moment: Instant? = null,
    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: User? = null,
    @OneToMany(mappedBy = "id.order")
    val items : Set<OrderItem> = setOf()
) : Serializable {

    fun getOrderStatus() : Int? {
        return orderStatus?.code
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as Order
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}