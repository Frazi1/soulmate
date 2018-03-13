package com.soulmate.services

import com.soulmate.models.Member
import com.soulmate.repositories.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberService {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    fun getByEmail(email: String): Member = memberRepository.findByEmail(email)
}