package com.neo.api.services

import com.neo.api.entities.User
import com.neo.api.repositories.UserRepository
import com.neo.api.services.excpetions.DatabaseException
import com.neo.api.services.excpetions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired
    private val userRepository: UserRepository
) {
    fun getAll(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): User {
        val optional = userRepository.findById(id)
        return optional.orElseThrow { ResourceNotFoundException(id) }
    }
    fun addUser(user: User): User = userRepository.save(user)

    fun deleteUser(id: Long) : Unit {
        try {
            userRepository.deleteById(id)
        } catch (e : EmptyResultDataAccessException) {
            throw ResourceNotFoundException(id)
        } catch (e : DataIntegrityViolationException) {
            throw DatabaseException(e.message)
        }
    }

    fun update(id : Long, user: User) : User {
        val optional = userRepository.findById(id)
        val newUser = optional.orElseThrow { ResourceNotFoundException(id) }

        newUser.name = user.name
        newUser.email = user.email

        return userRepository.save(newUser)
    }
}