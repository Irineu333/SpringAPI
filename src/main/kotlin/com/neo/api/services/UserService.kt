package com.neo.api.services

import com.neo.api.entities.User
import com.neo.api.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired
    private val userRepository: UserRepository
) {
    fun getAll() : List<User> = userRepository.findAll()
    fun getUserById(id : Long) : User = userRepository.getById(id)
}