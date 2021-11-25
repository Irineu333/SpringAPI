package com.neo.api.resources

import com.neo.api.services.CategoryService
import com.neo.api.services.OrderService
import com.neo.api.services.ProductService
import com.neo.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HomeResource(
    @Autowired
    private val productService: ProductService,
    @Autowired
    private val categoryService: CategoryService,
    @Autowired
    private val userService: UserService,
    @Autowired
    private val orderService: OrderService
) {

    @GetMapping
    fun getHome() = mapOf(
        "products" to productService.getAll().size,
        "categories" to categoryService.getAll().size,
        "users" to userService.getAll().size,
        "orders" to orderService.getAll().size
    )
}