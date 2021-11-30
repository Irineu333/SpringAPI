package com.neo.api.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.neo.api.entities.pk.OrderItemPK
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_order_item")
class OrderItem(
    @EmbeddedId
    @Column(nullable = false)
    @JsonIgnore
    var id: OrderItemPK? = null,
    product: Product? = null,
    order: Order? = null,
    @Column(nullable = false)
    val quantity: Int = 0,
) : Serializable {

    init {
       id = OrderItemPK(product, order)
    }

    fun setProduct(product: Product) {
        id?.product = product
    }

    fun getProduct(): Product? {
        return id?.product
    }

    fun setOrder(order: Order) {
        id?.order = order
    }

    @Column(nullable = false)
    val price: Double = product?.price ?: 0.0

    val subTotal : Double get() = price * quantity

    @JsonIgnore
    fun getOrder(): Order? {
        return id?.order
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderItem

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}