package com.neo.api.repositories

import com.neo.api.entities.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>