package com.neo.api.entities.pk

import com.fasterxml.jackson.annotation.JsonIgnore
import com.neo.api.entities.Order
import com.neo.api.entities.Product
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Embeddable
class OrderItemPK(
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null,
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    var order: Order? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderItemPK

        if (order != other.order) return false
        if (product != other.product) return false

        return true
    }

    override fun hashCode(): Int {
        var result = order?.hashCode() ?: 0
        result = 31 * result + (product?.hashCode() ?: 0)
        return result
    }

}