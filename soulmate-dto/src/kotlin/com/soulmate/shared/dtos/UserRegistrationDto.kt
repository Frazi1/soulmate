package com.soulmate.shared.dtos

data class UserRegistrationDto(var email: String = "",
                               var passwordHash: String = "",
                               var firstName: String = "")
