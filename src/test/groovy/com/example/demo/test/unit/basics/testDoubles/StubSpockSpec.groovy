package com.example.demo.test.unit.basics.testDoubles

import com.example.demo.test.unit.basics.customer.Customer
import com.example.demo.test.unit.basics.customer.CustomerRepository
import com.example.demo.test.unit.basics.customer.CustomerService
import com.example.demo.test.unit.basics.number.NumberService
import spock.lang.Specification

class StubSpockSpec extends Specification {

    NumberService numberService = Stub()

    CustomerRepository customerRepository = Stub()
    CustomerService customerService = new CustomerService(customerRepository)

    def "should get number from stubbed service"() {
        given:
        numberService.returningInt() >> 3

        expect:
        numberService.returningInt() == 3
    }

    def "should get customer from stubbed repository"() {
        given:
        customerRepository.findById(12) >> new Customer(12, "John", 23)

        when:
        Customer customer = customerService.findById(12)

        then:
        customer.name == "John"
        customer.age == 23
    }

    def "should save and return customer stubbed with argument matcher"() {
        given:
        customerRepository.save({ it.name == "John" && it.age == 23 }) >> new Customer(12, "John", 23)

        when:
        Customer customer = customerService.save(new Customer("John", 23))

        then:
        customer.name == "John"
        customer.age == 23
    }

    def "should execute custom save method"() {
        given:
        customerRepository.save(_) >> { args -> customSave(args[0]) }

        when:
        Customer customer = customerService.save(new Customer("John", 23))

        then:
        customer.name == "John"
        customer.age == 23
    }

    Customer customSave(Customer customer) {
        // execute custom save logic here
        customer
    }
}