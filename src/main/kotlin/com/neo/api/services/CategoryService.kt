package com.neo.api.services

import com.neo.api.entities.Category
import com.neo.api.repositories.CategoryRepository
import com.neo.api.services.excpetions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService(
    @Autowired
    private val categoryRepository: CategoryRepository
) {
    fun getAll(): List<Category> = categoryRepository.findAll()

    fun getCategoryById(id : Long) : Category {
        val optional = categoryRepository.findById(id)
        return optional.orElseThrow { ResourceNotFoundException(id) }
    }
}