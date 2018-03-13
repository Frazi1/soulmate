package com.soulmate.services

import com.soulmate.security.MemberDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService : UserDetailsService {

    @Autowired
    lateinit var memberService: MemberService

    override fun loadUserByUsername(name: String): UserDetails =
            MemberDetails(memberService.getByEmail(name))
}