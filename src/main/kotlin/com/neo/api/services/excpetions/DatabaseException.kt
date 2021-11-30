package com.neo.api.services.excpetions

class DatabaseException(
    val msg: String?
): RuntimeException(msg)