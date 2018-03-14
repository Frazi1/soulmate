package com.soulmate.security

import com.soulmate.models.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberDetails(private val member: Member) : UserDetails {
    override fun getUsername(): String = member.email
    override fun getPassword(): String = member.passwordHash
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(GrantedAuthority { "Admin" })
}