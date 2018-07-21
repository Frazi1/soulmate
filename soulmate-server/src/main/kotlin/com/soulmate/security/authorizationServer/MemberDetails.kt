package com.soulmate.security.authorizationServer

import com.soulmate.models.dataAccess.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberDetails(val member: Member) : UserDetails {

    override fun getUsername(): String = member.email
    override fun getPassword(): String = member.passwordHash
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(GrantedAuthority { "ADMIN" })
}