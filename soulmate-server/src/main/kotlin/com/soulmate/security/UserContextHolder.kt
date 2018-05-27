package com.soulmate.security

import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.security.interfaces.IUserContextHolder
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserContextHolder : IUserContextHolder {
    override val currentUserId: Long
        get() {
            val authentication = SecurityContextHolder.getContext().authentication
            if (authentication is AnonymousAuthenticationToken)
                return -1
            val principal = authentication.principal
            if (principal is MemberDetails)
                return principal.member.id
            return -1
        }
}