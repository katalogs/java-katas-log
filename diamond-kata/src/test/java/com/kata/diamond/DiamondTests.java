package com.kata.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DiamondTests {

  @Test
  void should_print_A_when_letter_is_A() {
    String diamond = Diamond.print('A');

    assertThat(diamond).isEqualTo("A");
  }

  @ParameterizedTest
  @ValueSource(chars = {'$', '€'})
  void should_print_empty_string_when_input_is_not_a_letter(char input) {
    String diamond = Diamond.print(input);

    assertThat(diamond).isEmpty();
  }

  @Test
  void should_print_A_when_letter_is_a() {
    String diamond = Diamond.print('a');

    assertThat(diamond).isEqualTo("A");
  }


}