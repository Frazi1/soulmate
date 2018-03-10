package com.soulmate.mapping

import com.soulmate.dtos.UserAccountDto
import com.soulmate.models.UserAccount
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class MappingConfiguration {

    @Bean
    @Scope(value = "singleton")
    fun getMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        val typeMap = modelMapper.createTypeMap<UserAccount, UserAccountDto>()
//        typeMap.addMappings { it.map(UserAccount::id.getter, UserAccountDto::id.setter) }
//        typeMap.addMappings { it.map(UserAccount::firstName.getter, UserAccountDto::firstName.setter) }
//        typeMap.addMappings { it.map(UserAccount::lastName.getter, UserAccountDto::lastName.setter) }
        return modelMapper
    }
}
