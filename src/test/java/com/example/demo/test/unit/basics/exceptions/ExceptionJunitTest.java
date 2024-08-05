package com.example.demo.test.unit.basics.exceptions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.test.unit.basics.number.NumberService;
import org.junit.jupiter.api.Test;

class ExceptionJunitTest {

  NumberService numberService = mock(NumberService.class);

  @Test
  void shouldThrowExceptionFromVoidMethodOfStubbedServiceAndVerifyExceptionClass() {
	doThrow(new RuntimeException()).when(numberService).returningVoid();

	Throwable throwable = catchThrowable(() -> numberService.returningVoid());

	assertThat(throwable).isInstanceOf(RuntimeException.class);
  }

  @Test
  void shouldThrowExceptionFromIntMethodOfStubbedServiceAndVerifyExceptionClass() {
	when(numberService.returningInt()).thenThrow(new RuntimeException());

	Throwable throwable = catchThrowable(() -> numberService.returningInt());

	assertThat(throwable).isInstanceOf(RuntimeException.class);
  }

  @Test
  void shouldThrowExceptionFromStubbedServiceAndVerifyExceptionMessage() {
	when(numberService.returningInt()).thenThrow(new RuntimeException("test"));

	Throwable throwable = catchThrowable(() -> numberService.returningInt());

	assertThat(throwable.getMessage()).isEqualTo("test");
  }
}
