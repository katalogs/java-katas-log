package com.kata.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
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

  @ParameterizedTest
  @ValueSource(chars = {'B', 'C'})
  void should_contain_twice_the_letter_on_every_lines_except_on_first_and_last_line(char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = diamond.split("\n");
    var subDiamond = Arrays.asList(splitDiamond).subList(1, splitDiamond.length - 1);
    assertThat(subDiamond).allSatisfy(line -> {
      assertThat(line.strip().toCharArray()).hasSize(2);
      assertThat((int) Stream.of(line.strip().toCharArray()).count()).isEqualTo(1);
    });
  }

  @ParameterizedTest
  @ValueSource(chars = {'B', 'C'})
  void should_be_symmetric_horizontally(char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = diamond.split("\n");
    int middleLine = letter - 'A';
    var upper = Arrays.asList(splitDiamond).subList(0, middleLine);
    var lower = Arrays.asList(splitDiamond).subList(middleLine + 1, splitDiamond.length);
    assertThat(upper.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList())).isEqualTo(lower);
  }

  @Property
  boolean should_contain_one_A_on_first_and_last_line2(@ForAll("letters") char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = diamond.split("\n");
    return splitDiamond[0].strip().equals("A") && splitDiamond[splitDiamond.length - 1].strip().equals("A");
  }

  @Provide
  Arbitrary<Character> letters() {
    return Arbitraries.chars().alpha();
  }
}