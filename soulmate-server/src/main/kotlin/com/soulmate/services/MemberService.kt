package com.soulmate.services

import com.soulmate.models.Member
import com.soulmate.models.UserAccount
import com.soulmate.models.mapping.toMember
import com.soulmate.repositories.MemberRepository
import com.soulmate.repositories.UserRepository
import com.soulmate.security.authorizationServer.MemberDetails
import dtos.UserRegistrationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service(value = "MemberService")
class MemberService : UserDetailsService {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    fun getByEmail(email: String): Member? = memberRepository.findByEmail(email)
    fun exists(email: String): Boolean = memberRepository.existsByEmail(email)

    override fun loadUserByUsername(name: String): UserDetails? {
        val member: Member? = getByEmail(name)
        return if (member != null)
            MemberDetails(member)
        else
            null
    }

    fun registerMember(userRegistrationDto: UserRegistrationDto) {
        val member = userRegistrationDto.toMember()
        val userAccount = UserAccount()
        member.userAccount = userAccount
        userAccount.member = member
        memberRepository.save(member)
    }
}