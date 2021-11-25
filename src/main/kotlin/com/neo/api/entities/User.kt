package com.neo.api.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_user")
data class User(
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    val name: String = "",
    @Column(nullable = false)
    val email: String = "",
    @JsonIgnore
    @Column(nullable = false)
    val pwHash : String = "",
    @JsonIgnore
    @OneToMany(mappedBy = "client") //optional
    val orders : List<Order> = listOf()
) : Serializable {

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