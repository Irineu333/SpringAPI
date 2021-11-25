package com.neo.api.repositories

import com.neo.api.entities.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>