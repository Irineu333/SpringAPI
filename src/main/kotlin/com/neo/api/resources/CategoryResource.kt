package com.neo.api.resources

import com.neo.api.entities.Category
import com.neo.api.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryResource(
    @Autowired
    private val categoryService: CategoryService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Category>> {
        val categories = categoryService.getAll()
        return ResponseEntity.ok(categories)
    }

    @GetMapping(value = ["/{id}"])
    fun getCategoryById(@PathVariable id : Long) : ResponseEntity<Category> {
        val category = categoryService.getCategoryById(id)
        return ResponseEntity.ok(category)
    }
}