package com.example.demo.test.unit.basics.prepareTestData;

import static com.example.demo.test.unit.basics.TestData.getCustomer;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.test.unit.basics.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LombokChainedSetterTest {

  @Test
  @DisplayName("should get customer")
  void shouldGetCustomer() {
	// given
	Customer customer = getCustomer().setName("Invalid name");

	// then
	assertThat(customer.getName()).isEqualTo("Invalid name");
  }
}
