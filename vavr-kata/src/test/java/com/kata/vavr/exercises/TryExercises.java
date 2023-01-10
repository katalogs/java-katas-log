package com.kata.vavr.exercises;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * By doing these exercises you should have learned :
 * {@link Try#get()}<br>
 * {@link Try#isSuccess()}<br>
 * {@link Try#isEmpty()}<br>
 * {@link Try#isFailure()}<br>
 * {@link Try#getOrElse(Object)}<br>
 * {@link Try#onFailure(Consumer)}<br>
 * {@link Try#onSuccess(Consumer)}<br>
 * {@link Try#flatMap(Function)}<br>
 */
class TryExercises extends PetDomainKata {

  private final String SUCCESS_MESSAGE = "I m a fucking genius the result is ";

  private Try<Integer> divide(Integer x, Integer y) {
    return Try.of(() -> x / y);
  }

  @Test
  void getTheResultOfDivide() {
    // Divide x = 9 by y = 2
    Try<Integer> tryResult = divide(9, 2);
    Integer result = tryResult.get();

    assertThat(result).isEqualTo(4);
    assertThat(tryResult.isSuccess()).isTrue();
    assertThat(tryResult.isEmpty()).isFalse();
    assertThat(tryResult.isFailure()).isFalse();
  }

  @Test
  void mapTheResultOfDivide() {
    // Divide x = 9 by y = 2 and add z to the result
    Integer z = 3;
    Integer result = divide(9,2)
        .map(v -> v + z)
        .get();

    assertThat(result).isEqualTo(7);
  }

  @Test
  void divideByZeroIsAlwaysAGoodIdea() {
    // Divide x by 0 and get the result
    assertThatThrownBy(() -> divide(7891562, 0).get())
        .isInstanceOf(ArithmeticException.class);
  }

  @Test
  void divideByZeroOrElse() {
    // Divide x by 0, on exception returns 0
    Integer x = 1;
    Integer result = divide(x, 0)
        .getOrElse(0);

    assertThat(result).isEqualTo(0);
  }

  @Test
  void mapTheFailure() {
    // Divide x by 0, log the failure message to the console and get 0
    Integer x = 1;
    Integer result = divide(x, 0)
        .onFailure(t -> System.out.println("Cannot divide value " + x + ": " + t.getMessage()))
        .getOrElse(0);

    assertThat(result).isEqualTo(0);
  }

  @Test
  void mapTheSuccess() {
    // Divide x by y
    // log the failure message to the console
    // Log your success to the console
    // Get the result or 0 if exception
    Integer x = 8;
    Integer y = 4;

    Integer result = divide(x, y)
        .onSuccess(v -> System.out.println("x/y = " + v))
        .onFailure(t -> System.out.println("Cannot divide value " + x + ": " + t.getMessage()))
        .getOrElse(0);

    assertThat(result).isEqualTo(2);
  }

  @Test
  void chainTheTry() {
    // Divide x by y
    // Chain 2 other calls to divide with x = previous Divide result
    // log the failure message to the console
    // Log your success to the console
    // Get the result or 0 if exception
    Integer x = 27;
    Integer y = 3;

    Integer result = divide(x, y)
        .flatMap(v -> divide(v, y))
        .flatMap(v -> divide(v, y))
        .onSuccess(v -> System.out.println("x/y 3 times = " + v))
        .onFailure(t -> System.out.println("Cannot divide value " + x + ": " + t.getMessage()))
        .getOrElse(0);

    assertThat(result).isEqualTo(1);
  }
}