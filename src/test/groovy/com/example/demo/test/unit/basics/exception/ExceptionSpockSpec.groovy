package com.example.demo.test.unit.basics.exception


import com.example.demo.test.unit.basics.number.NumberService
import spock.lang.Specification

class ExceptionSpockSpec extends Specification {

    NumberService numberService = Stub()

    def "should throw exception from void method of stubbed service and verify exception class"() {
        given:
        numberService.returningVoid() >> { throw new IllegalArgumentException() }

        when:
        numberService.returningVoid()

        then:
        thrown IllegalArgumentException
    }

    def "should throw exception from stubbed service and verify exception class"() {
        given:
        numberService.returningInt() >> { throw new IllegalArgumentException() }

        when:
        numberService.returningInt()

        then:
        thrown IllegalArgumentException
    }

    def "should throw exception from stubbed service and verify exception message"() {
        given:
        numberService.returningInt() >> { throw new IllegalArgumentException("test") }

        when:
        numberService.returningInt()

        then:
        IllegalArgumentException thrown = thrown()
        thrown.message == "test"
    }
}
