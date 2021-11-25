package com.neo.api.repositories

import com.neo.api.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//@Repository
interface UserRepository : JpaRepository<User, Long>