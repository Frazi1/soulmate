package com.soulmate.mappingTest

import com.soulmate.dtos.UserAccountDto
import com.soulmate.mapping.map
import com.soulmate.models.UserAccount
import org.junit.Test
import org.junit.runner.RunWith
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class MappingTests {

    @Autowired
    lateinit var mapper: ModelMapper

    @Test
    fun simpleMapping() {
        val e = UserAccount(100, null, "Dima", "Vychikov")
        val d = mapper.map<UserAccountDto>(e)

        assert(e.id == d.id)
        assert(e.firstName == d.firstName)
        assert(e.lastName == d.lastName)
    }
}