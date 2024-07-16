package com.example.demo.test.unit.pitest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class StudentFacade {

  private final StudentRepository studentRepository;
  private final StudentEventPublisher studentEventPublisher;

  Student save(Student student) {
	log.debug("saving student={}", student);
	validate(student);
	studentEventPublisher.publishStudentSavedEvent(student);
	studentRepository.save(student);
	return student;
  }

  void validate(Student student) {
	if (student.getName().length() < 2) {
	  throw new IllegalArgumentException("name is too short");
	}
	if (student.getAge() > 125) {
	  throw new IllegalArgumentException("age is too high");
	}
  }
}
