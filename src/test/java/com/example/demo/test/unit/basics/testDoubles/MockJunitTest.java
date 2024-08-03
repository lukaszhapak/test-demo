package com.example.demo.test.unit.basics.testDoubles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.demo.test.unit.basics.customer.Customer;
import com.example.demo.test.unit.basics.customer.CustomerRepository;
import com.example.demo.test.unit.basics.customer.CustomerService;
import com.example.demo.test.unit.basics.number.NumberService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class MockJunitTest {

  NumberService numberService = mock(NumberService.class);

  ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
  CustomerRepository customerRepository = mock(CustomerRepository.class);
  CustomerService customerService = new CustomerService(customerRepository);

  @Test
  void shouldVerifyCallOnVoidMethodOfMockedService() {
	// when
	numberService.returningVoid();

	// then
	verify(numberService).returningVoid();
  }

  @Test
  void shouldVerifyCallOnMockedService() {
	// when
	numberService.returningInt();

	// then
	verify(numberService).returningInt();
  }

  @Test
  void shouldVerifyCallWithArgumentOnMockedService() {
	// when
	numberService.returningInt(5);

	// then
	verify(numberService).returningInt(5);
  }

  @Test
  void shouldSaveCustomerAndVerifyItsFields() {
	// when
	customerService.save(new Customer("John", 22));

	// then
	verify(customerRepository).save(argThat(arg -> arg.getName().equals("John") && arg.getAge() == (22)));
  }

  @Test
  void shouldSaveCustomerAndCaptureObject() {
	// when
	customerService.save(new Customer("John", 22));

	// then
	verify(customerRepository).save(customerArgumentCaptor.capture());
	Customer customerArgumentCaptorValue = customerArgumentCaptor.getValue();

	assertThat(customerArgumentCaptorValue.getName()).isEqualTo("John");
	assertThat(customerArgumentCaptorValue.getAge()).isEqualTo(22);
  }
}
