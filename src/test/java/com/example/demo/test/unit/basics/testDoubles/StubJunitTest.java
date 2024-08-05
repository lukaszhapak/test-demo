package com.example.demo.test.unit.basics.testDoubles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.test.unit.basics.customer.Customer;
import com.example.demo.test.unit.basics.customer.CustomerRepository;
import com.example.demo.test.unit.basics.customer.CustomerService;
import com.example.demo.test.unit.basics.number.NumberService;
import org.junit.jupiter.api.Test;

class StubJunitTest {

  NumberService numberService = mock(NumberService.class);

  CustomerRepository customerRepository = mock(CustomerRepository.class);
  CustomerService customerService = new CustomerService(customerRepository);

  @Test
  void shouldGetNumberFromStubbedService() {
	// given
	when(numberService.returningInt()).thenReturn(3);

	// then
	assertThat(numberService.returningInt()).isEqualTo(3);
  }

  @Test
  void shouldExecuteRealIntMethod() {
	when(numberService.returningInt()).thenCallRealMethod();

	assertThat(numberService.returningInt()).isEqualTo(5);
  }

  @Test
  void shouldExecuteRealVoidMethod() {
	doCallRealMethod().when(numberService).returningVoid();

	numberService.returningVoid();
  }

  @Test
  void shouldGetCustomerFromStubbedRepository() {
	// given
	when(customerRepository.findById(12L)).thenReturn(new Customer(12L, "John", 22));

	// when
	Customer customer = customerService.findById(12L);

	// then
	assertThat(customer.getName()).isEqualTo("John");
	assertThat(customer.getAge()).isEqualTo(22);
  }

  @Test
  void shouldSaveAndReturnCustomerStubbedWithArgumentMatcher() {
	// given
	when(customerRepository.save(argThat(it -> it.getName().equals("John") && it.getAge() == 22))).thenReturn(new Customer(12L, "John", 22));

	// when
	Customer customer = customerService.save(new Customer("John", 22));

	// then
	assertThat(customer.getName()).isEqualTo("John");
	assertThat(customer.getAge()).isEqualTo(22);
  }

  @Test
  void shouldReturnInsertedValue() {
	// given
	when(customerRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

	// when
	Customer customer = customerService.save(new Customer("John", 22));

	// then
	assertThat(customer.getName()).isEqualTo("John");
	assertThat(customer.getAge()).isEqualTo(22);
  }

  @Test
  void shouldExecuteCustomSaveMethod() {
	// given
	when(customerRepository.save(any())).thenAnswer(invocation -> customSave(invocation.getArgument(0)));

	// when
	Customer customer = customerService.save(new Customer("John", 22));

	// then
	assertThat(customer.getId()).isEqualTo(1L);
	assertThat(customer.getName()).isEqualTo("John");
	assertThat(customer.getAge()).isEqualTo(22);
  }

  Customer customSave(Customer customer) {
	// execute custom save logic here
	customer.setId(1L);
	return customer;
  }
}
