package com.neo.api.resources

import com.neo.api.entities.User
import com.neo.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/users")
class UserResource(
    @Autowired
    private val userService: UserService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<User>> {
        val users = userService.getAll()
        return ResponseEntity.ok(users)
    }

    @GetMapping(value = ["/{id}"])
    fun getById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun add(@RequestBody(required = true) user: User): ResponseEntity<User> {
        val userAdded = userService.addUser(user)

        val location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(user.id)
            .toUri()

        return ResponseEntity.created(location).body(userAdded)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping(value = ["/{id}"])
    fun update(
        @PathVariable id: Long,
        @RequestBody user: User
    ): ResponseEntity<User> {
        val oderUpdated = userService.update(id, user)
        return ResponseEntity.ok(oderUpdated)
    }
}