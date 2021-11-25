package com.neo.api.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_product")
data class Product(
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    val name: String = "",
    val description: String? = null,
    @Column(nullable = false)
    val price: Double = 0.0,
    val imgUrl: String? = null,
    @ManyToMany
    @JoinTable(
        name = "tb_product_category",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: Set<Category> = setOf(),
    @OneToMany(mappedBy = "id.product")
    private val items : Set<OrderItem> = setOf()
) : Serializable {

    @JsonIgnore
    fun getOrders() : List<Order?>{
        return items.map { it.getOrder() }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as Product
        return id != null && id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}