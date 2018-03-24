package com.soulmate.mapping

import com.soulmate.dtos.UserAccountDto
import com.soulmate.models.Member
import com.soulmate.models.UserAccount
import dtos.UserRegistrationDto

fun UserAccount.toUserAccountDto(): UserAccountDto {
    return UserAccountDto(this.id, this.firstName, this.firstName)
}

fun UserAccountDto.toUserAccount(): UserAccount {
    return UserAccount(this.id, null, this.firstName, this.lastName)
}

fun Member.toUserRegistrationDto(): UserRegistrationDto {
    return UserRegistrationDto(this.email,
            this.passwordHash,
            if (this.userAccount != null) this.userAccount!!.firstName else "")
}

fun UserRegistrationDto.toMember(): Member {
    return Member(this.email, this.passwordHash, null)
}
