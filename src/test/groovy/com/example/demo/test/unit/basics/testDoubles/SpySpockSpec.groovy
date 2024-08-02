package com.example.demo.test.unit.basics.testDoubles

import com.example.demo.test.unit.basics.customer.Customer
import com.example.demo.test.unit.basics.customer.CustomerRepository
import com.example.demo.test.unit.basics.customer.CustomerService
import com.example.demo.test.unit.basics.number.NumberService
import spock.lang.Specification

class SpySpockSpec extends Specification {

    NumberService numberService = Spy()

    CustomerRepository customerRepository = Stub()
    CustomerService customerService = Spy(new CustomerService(customerRepository))

    def "should get number from spy service"() {
        expect:
        numberService.returningInt() == 5
    }

    def "should get customer from spy service with stubbed repository"() {
        given:
        customerRepository.findById(1) >> new Customer(1, "John", 23)

        expect:
        customerService.findById(1).name == "John"
    }

    def "should get customer from spy service with stubbed service"() {
        given:
        customerService.findById(1) >> new Customer(1, "John", 23)

        expect:
        customerService.findById(1).name == "John"
    }
}