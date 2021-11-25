package com.neo.api.resources

import com.neo.api.entities.Order
import com.neo.api.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderResource(
    @Autowired
    private val orderService : OrderService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Order>> {
        val orders = orderService.getAll()
        return ResponseEntity.ok(orders)
    }

    @GetMapping(params = ["clientId"])
    fun findByUserId(@RequestParam clientId : Long): ResponseEntity<List<Order>> {
        val orders = orderService.getOrdersByUserId(clientId)
        return ResponseEntity.ok(orders)
    }

    @GetMapping(value = ["/{id}"])
    fun getOrderById(@PathVariable id : Long) : ResponseEntity<Order> {
        val order = orderService.getOrderById(id)
        return ResponseEntity.ok(order)
    }
}