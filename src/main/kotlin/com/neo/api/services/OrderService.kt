package com.neo.api.services

import com.neo.api.entities.Order
import com.neo.api.entities.User
import com.neo.api.repositories.OrderRepository
import com.neo.api.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(
    @Autowired
    private val orderRepository : OrderRepository,
    @Autowired
    private val userService: UserService
) {
    fun getAll() : List<Order> = orderRepository.findAll()
    fun getOrdersByUserId(userId : Long) : List<Order> = userService.getUserById(userId).orders
    fun getOrderById(id : Long) : Order = orderRepository.getById(id)
}