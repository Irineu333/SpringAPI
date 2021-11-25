package com.neo.api.util

import org.jetbrains.annotations.NonNls
import java.security.MessageDigest

fun String.md5(): ByteArray = this.digest("MD5")

fun String.sha256(): ByteArray = this.digest("SHA-256")

fun String.digest(@NonNls algorithm : String): ByteArray =
    MessageDigest.getInstance(algorithm).digest(this.toByteArray(Charsets.UTF_8))

fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }
