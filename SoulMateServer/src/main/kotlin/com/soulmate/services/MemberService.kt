package com.soulmate.services

import com.soulmate.models.Member
import com.soulmate.repositories.MemberRepository
import com.soulmate.security.MemberDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MemberService : UserDetailsService {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    fun getByEmail(email: String): Member = memberRepository.findByEmail(email)

    override fun loadUserByUsername(name: String): UserDetails =
            MemberDetails(getByEmail(name))
}