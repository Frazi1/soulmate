package com.soulmate.services

import com.soulmate.models.Member
import com.soulmate.models.UserAccount
import com.soulmate.repositories.MemberRepository
import com.soulmate.repositories.UserRepository
import com.soulmate.security.authorizationServer.MemberDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service(value = "MemberService")
class MemberService : UserDetailsService/*, ClientDetailsService*/ {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var userRepository: UserRepository

    fun getByEmail(email: String): Member? = memberRepository.findByEmail(email)
    fun exists(email: String): Boolean = memberRepository.existsByEmail(email)

    override fun loadUserByUsername(name: String): UserDetails? {
        val member: Member? = getByEmail(name)
        return if (member != null)
            MemberDetails(member)
        else
            null
    }

//    fun add(member: Member) {
//        memberRepository.save(member)
//    }

    fun registerMember(member: Member) {
        val userAccount = UserAccount()
        member.userAccount = userAccount
        userAccount.member = member
        memberRepository.save(member)
    }

//    override fun loadClientByClientId(id: String): ClientDetails {
//
//    }
}