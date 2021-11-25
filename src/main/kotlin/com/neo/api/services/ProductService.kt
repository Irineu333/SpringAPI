package com.neo.api.services

import com.neo.api.entities.Category
import com.neo.api.entities.Product
import com.neo.api.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(
    @Autowired
    private val productRepository: ProductRepository,
    @Autowired
    private val categoryService: CategoryService
) {
    fun getAll(): List<Product> = productRepository.findAll()
    fun getProductById(id: Long): Product = productRepository.getById(id)

    fun getProductByCategoryId(categoryId: Long): Set<Product> =
        categoryService.getCategoryById(categoryId).products
}