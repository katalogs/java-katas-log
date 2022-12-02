package com.kata.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
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

//  @Test
//  void should_print_ABBA_when_letter_is_B() {
//    String diamond = Diamond.print('B');
//
//    assertThat(diamond).isEqualTo("ABBA");
//  }
//
//  @Test
//  void should_print_ABBCCBBA_when_letter_is_C() {
//    String diamond = Diamond.print('C');
//
//    assertThat(diamond).isEqualTo("ABBCCBBA");
//  }
//
//  @Test
//  void should_print_ABBCCDDCCBBA_when_letter_is_D() {
//    String diamond = Diamond.print('D');
//
//    assertThat(diamond).isEqualTo("ABBCCDDCCBBA");
//  }

  @ParameterizedTest
  @ValueSource(chars = {'A', 'B', 'C'})
  void should_contain_one_A_on_first_and_last_line(char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = diamond.split("\n");
    assertThat(splitDiamond[0].strip()).isEqualTo("A");
    assertThat(splitDiamond[splitDiamond.length - 1].strip()).isEqualTo("A");
  }

  @ParameterizedTest
  @ValueSource(chars = {'B', 'C'})
  void should_contain_twice_the_letter_on_every_lines_except_on_first_and_last_line(char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = diamond.split("\n");
    var subDiamond = Arrays.asList(splitDiamond).subList(1, splitDiamond.length - 1);
    assertThat(subDiamond).allSatisfy(line -> {
      assertThat(line.strip().toCharArray()).hasSize(2);
      assertThat((int) List.of(line.strip().toCharArray()).stream().distinct().count()).isEqualTo(1);
    });
  }

}