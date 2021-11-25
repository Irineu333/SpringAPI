package com.neo.api.config

import com.neo.api.entities.*
import com.neo.api.entities.enums.OrderStatus
import com.neo.api.repositories.*
import com.neo.api.util.sha256
import com.neo.api.util.toHex
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.time.Instant

@Configuration
@Profile("test")
class TestConfig(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val orderRepository: OrderRepository,
    @Autowired
    private val categoryRepository: CategoryRepository,
    @Autowired
    private val productRepository: ProductRepository,
    @Autowired
    private val orderItemRepository: OrderItemRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {

        val category1 = Category(null,"Electronics")
        val category2 = Category(null,"Books")
        val category3 = Category(null,"Computers")
        categoryRepository.saveAll(listOf(category1, category2, category3))

        val product1 = Product(null, "TV", price = 20.0, categories = setOf(category1))
        val product2 = Product(null, "Smartphone", price = 10.0, categories = setOf(category1))
        val product3 = Product(null,"Notebook", price = 5.0, categories = setOf(category1, category3))
        productRepository.saveAll(listOf(product1, product2, product3))

        val user1 = User(null, "Irineu", "mmmirineusilva@gmail.com", "12345".sha256().toHex())
        val user2 = User(null, "Carlos", "carlos@gmail.com", "meuOvo".sha256().toHex())
        val user3 = User(null,"Kleber", "kleber@gmail.com", "123456".sha256().toHex())
        userRepository.saveAll(listOf(user1, user2, user3))

        val order1 = Order(null, OrderStatus.PAID, Instant.now(), user2)
        val order2 = Order(null,OrderStatus.CANCELED, Instant.now(), user2)
        val order3 = Order(null,OrderStatus.WAITING_PAYMENT, Instant.now(), user1)
        orderRepository.saveAll(listOf(order1, order2, order3))

        val orderItem1 = OrderItem(null, product1, order2, 2, product1.price)
        orderItemRepository.saveAll(listOf(orderItem1))
    }
}
