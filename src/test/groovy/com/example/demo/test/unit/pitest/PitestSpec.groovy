package com.example.demo.test.unit.pitest

import spock.lang.Specification

class PitestSpec extends Specification {

    StudentRepository studentRepository = Mock()
    StudentEventPublisher studentEventPublisher = Mock()
    StudentFacade studentFacade = new StudentFacade(studentRepository, studentEventPublisher)
    Student student = new Student("John", 21)

    def "should save valid student"() {
        when:
        Student response = studentFacade.save(student)

        then:
        response.getName() == "John"
        1 * studentEventPublisher.publishStudentSavedEvent(_)
        1 * studentRepository.save(_)
    }

    def "should throw exception when name is too short"() {
        given:
        student.setName("J")

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * studentEventPublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }

    def "should throw exception when age is too high"() {
        given:
        student.setAge(131)

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * studentEventPublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }
}
