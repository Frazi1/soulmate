package dtos

import com.soulmate.dtos.UserAccountDto

data class UserRegistrationDto(var email: String = "",
                               var passwordHash: String = "",
                               var firstName: String = "")
