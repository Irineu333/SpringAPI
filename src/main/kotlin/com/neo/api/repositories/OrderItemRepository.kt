package com.neo.api.repositories

import com.neo.api.entities.OrderItem
import com.neo.api.entities.pk.OrderItemPK
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository : JpaRepository<OrderItem, OrderItemPK>