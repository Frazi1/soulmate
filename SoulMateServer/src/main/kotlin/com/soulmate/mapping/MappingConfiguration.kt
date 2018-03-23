package com.soulmate.mapping

import com.soulmate.dtos.UserAccountDto
import com.soulmate.models.Member
import com.soulmate.models.UserAccount
import dtos.UserRegistrationDto
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class MappingConfiguration {

    @Bean
    @Scope(value = "singleton")
    fun getMapper(): ModelMapper {
        val m = ModelMapper()
        val typeMap = m.createTypeMap<UserAccount, UserAccountDto>()
        m.createTypeMap<Member, UserRegistrationDto>()
//        typeMap.addMappings { it.map(UserAccount::id.getter, UserAccountDto::id.setter) }
//        typeMap.addMappings { it.map(UserAccount::firstName.getter, UserAccountDto::firstName.setter) }
//        typeMap.addMappings { it.map(UserAccount::lastName.getter, UserAccountDto::lastName.setter) }
        return m
    }
}
