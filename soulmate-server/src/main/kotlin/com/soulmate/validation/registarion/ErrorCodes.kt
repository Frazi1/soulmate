package com.soulmate.validation.registarion

enum class ErrorCodes(val description: String) {
    USER_ALREADY_EXISTS("User already exists"),
    USER_NAME_IS_EMPTY("User name must not me empty"),
    PASSWORD_IS_EMPTY("Password must not be empty")
}