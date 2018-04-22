package com.soulmate.validation.exceptions

open class SoulmateBusinessException(message: String) : Exception(message)

class SoulmateUserDoesNotExistException(userId: Long): SoulmateBusinessException("User with ID $userId does not exist")