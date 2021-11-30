package com.neo.api.services.excpetions

class ResourceNotFoundException(
    val id: Long
) : RuntimeException("Resource not found. Id $id")