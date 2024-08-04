package com.example.demo.test.unit.basics.testDoubles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.example.demo.test.unit.basics.customer.Customer;
import com.example.demo.test.unit.basics.customer.CustomerRepository;
import com.example.demo.test.unit.basics.customer.CustomerService;
import com.example.demo.test.unit.basics.number.NumberService;
import org.junit.jupiter.api.Test;

class SpyJunitTest {

  NumberService numberService = spy(NumberService.class);

  CustomerRepository customerRepository = mock(CustomerRepository.class);
  CustomerService customerService = spy(new CustomerService(customerRepository));

  @Test
  void shouldGetNumberFromStubbedMethodOfSpyService() {
	when(numberService.returningInt()).thenReturn(3);

	assertThat(numberService.returningInt()).isEqualTo(3);
  }

  @Test
  void shouldGetNumberFromRealMethodOfSpyService() {
	assertThat(numberService.returningInt()).isEqualTo(5);
  }

  @Test
  void shouldGetCustomerFromSpyServiceWithStubbedRepository() {
	when(customerRepository.findById(1L)).thenReturn( new Customer(1L, "John", 23));

	assertThat(customerService.findById(1L).getName()).isEqualTo("John");
  }

  @Test
  void shouldGetCustomerFromSpyServiceWithStubbedMethod() {
	when(customerService.findById(1L)).thenReturn( new Customer(1L, "John", 23));

	assertThat(customerService.findById(1L).getName()).isEqualTo("John");
  }
}
