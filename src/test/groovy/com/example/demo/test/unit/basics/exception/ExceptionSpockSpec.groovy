package com.example.demo.test.unit.basics.exception

import com.example.demo.test.unit.basics.number.NumberService
import spock.lang.Specification

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable

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

    def "should get throw exception from subbed service method executed in helper method"() {
        given:
        numberService.returningInt() >> { throw new IllegalArgumentException("test") }

        expect:
        getException().message == "test"
    }

    IllegalArgumentException getException() {
        // using assertJ here i was not able to find a way how to do that in spock
        catchThrowable(() -> numberService.returningInt())
    }
}
