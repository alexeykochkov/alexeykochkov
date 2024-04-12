package com.example.unitest


import kotlinx.coroutines.*
import org.junit.Test

interface Person {
    abstract fun getFirstName(): String?
}

class Bus() {
    fun addPerson(person: Person) {
        println("person first name: ${person.getFirstName()}")
    }
}

class PersonStub() : Person {
    override fun getFirstName(): String? {
        return "Dolores"
    }
}

class PersonImpl() : Person {
    override fun getFirstName(): String? {
        val retrofit = RetrofitInstance.searchUserApi
        var random: User? = null
        runBlocking {
            random = retrofit.getRandomUser()
            System.out.println(random)
        }
        return random?.results?.get(0)?.name?.first
    }
}

class RetrofitUnitTest {

    @Test
    fun testInit() {
        val person = PersonImpl()
        var name = person.getFirstName()
        println(name)
    }

    @Test
    fun testPersonUsageRealApp() {
        val person = PersonImpl()
        val bus = Bus()
        bus.addPerson(person)
    }

    @Test
    fun testPersonUsageStubTestApp() {
        val person = PersonStub()
        val bus = Bus()
        bus.addPerson(person)
    }
}