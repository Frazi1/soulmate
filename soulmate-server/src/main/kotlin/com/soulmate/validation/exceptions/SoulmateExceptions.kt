package com.soulmate.validation.exceptions

open class BusinessException(message: String) : Exception(message)

class UserDoesNotExistException(userId: Long): BusinessException("User with ID $userId does not exist")
