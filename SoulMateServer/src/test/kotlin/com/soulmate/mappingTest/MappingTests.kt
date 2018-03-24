package com.soulmate.mappingTest

import org.junit.Test
import org.junit.runner.RunWith
import org.mapstruct.factory.Mappers
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class MappingTests {

//    @Autowired
//    @Qualifier(value = "TestMapper")
//    lateinit var mapper: ModelMapper

//    @Test
//    fun simpleMapping() {
//        val e = UserAccount(100, null, "Dima", "Vychikov")
//        val d = mapper.map<UserAccountDto>(e)
//
//        assert(e.id == d.id)
//        assert(e.firstName == d.firstName)
//        assert(e.lastName == d.lastName)
//    }

    @Test
    fun configurationTest() {
        val a = Stub1()
        a.stub1 = "test"
        val b = Mappers.getMapper(StubConverter::class.java).toStub2(a)
        assert(b.stub2 == "test")
    }
}