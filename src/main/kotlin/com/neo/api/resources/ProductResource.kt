package com.neo.api.resources

import com.neo.api.entities.Order
import com.neo.api.entities.Product
import com.neo.api.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductResource(
    @Autowired
    private val productService: ProductService
) {
    @GetMapping
    fun getAll(): ResponseEntity<List<Product>> {
        val products = productService.getAll()
        return ResponseEntity.ok(products)
    }

    @GetMapping(params = ["categoryId"])
    fun findByCategoryId(@RequestParam categoryId : Long): ResponseEntity<Set<Product>> {
        val products = productService.getProductByCategoryId(categoryId)
        return ResponseEntity.ok(products)
    }

    @GetMapping(value = ["/{id}"])
    fun getProductById(@PathVariable id : Long) : ResponseEntity<Product> {
        val product = productService.getProductById(id)
        return ResponseEntity.ok(product)
    }
}