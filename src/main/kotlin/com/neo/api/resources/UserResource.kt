package com.neo.api.resources

import com.neo.api.entities.User
import com.neo.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserResource(
    @Autowired
    private val userService: UserService
) {

    @GetMapping
    fun getAll() : ResponseEntity<List<User>> {
        val users = userService.getAll()
        return ResponseEntity.ok(users)
    }

    @GetMapping(value = ["/{id}"])
    fun getUserById(@PathVariable id : Long) : ResponseEntity<User> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(user)
    }
}