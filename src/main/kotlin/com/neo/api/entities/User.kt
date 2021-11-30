package com.neo.api.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.juli.logging.Log
import org.hibernate.Hibernate
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.springframework.lang.NonNull
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_user")
class User() : Serializable {

    constructor(
        name: String,
        email: String,
        pwHash: String,
        orders: List<Order> = listOf()
    ) : this() {
        this.name = name
        this.email = email
        this.pwHash = pwHash
        this.orders = orders
    }

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    lateinit var name: String

    @Column(nullable = false)
    lateinit var email: String

    @Column(nullable = false)
    lateinit var pwHash: String

    @OneToMany(mappedBy = "client")
    @get:JsonIgnore
    var orders: List<Order> = listOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as User
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}