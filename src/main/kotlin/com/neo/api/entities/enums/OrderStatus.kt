package com.neo.api.entities.enums

enum class OrderStatus(val code: Int) {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    companion object {
        fun enumOf(code : Int) : OrderStatus {
            val orderStatus = values().find { it.code == code }

            checkNotNull(orderStatus) { "Invalid ${OrderStatus::name} code" }

            return orderStatus
        }

        fun isValid(code : Int) : Boolean = values().any{ it.code == code}
    }
}