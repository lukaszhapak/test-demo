package com.example.demo.test.unit.basics.number;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberRepository {

  public int returningInt() {
	log.debug("returning int");
	return 255;
  }
}
