package com.soulmate.validation.exceptions

open class SoulmateBusinessException(message: String) : Exception(message)

class UserDoesNotExistException(userId: Long): SoulmateBusinessException("User with ID $userId does not exist")