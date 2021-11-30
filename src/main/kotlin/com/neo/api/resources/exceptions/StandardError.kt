package com.neo.api.resources.exceptions

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.Instant

class StandardError(
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
        timezone = "GMT"
    )
    val timestamp: Instant,
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
) : Serializable