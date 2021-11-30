package com.neo.api.resources.exceptions

import com.neo.api.services.excpetions.DatabaseException
import com.neo.api.services.excpetions.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFound(
        e: ResourceNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {
        val error = "Resource not found"
        val status: HttpStatus = HttpStatus.NOT_FOUND

        val err: StandardError = StandardError(
            Instant.now(),
            status.value(),
            error,
            e.message,
            request.requestURI
        )

        return ResponseEntity.status(status).body(err)
    }

    @ExceptionHandler(DatabaseException::class)
    fun databaseException(
        e: DatabaseException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {
        val error = "Database error"
        val status = HttpStatus.BAD_REQUEST

        val err = StandardError(
            Instant.now(),
            status.value(),
            error,
            e.message,
            request.requestURI
        )

        return ResponseEntity.status(status).body(err)
    }
}