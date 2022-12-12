package com.kata.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.UpperChars;
import org.junit.jupiter.api.Test;


class DiamondTests {

  @Test
  void should_print_A_when_letter_is_A() {
    String diamond = Diamond.print('A');

    assertThat(diamond).isEqualTo("A");
  }

  @Property
  boolean should_print_empty_string_when_input_is_not_a_letter(@ForAll("noneLetterChar") char letter) {
    System.out.println(letter);
    String diamond = Diamond.print(letter);

    assertThat(diamond).isEmpty();

    return diamond.isEmpty();
  }

  @Property
  boolean should_contain_twice_the_letter_on_every_lines_except_on_first_and_last_line(@ForAll("upperLettersWithoutA") char letter) {

    String diamond = Diamond.print(letter);

    String[] splitDiamond = diamond.split("\n");
    var subDiamond = Arrays.asList(splitDiamond).subList(1, splitDiamond.length - 1);

    return subDiamond.stream().allMatch(line -> line.strip().toCharArray().length == 2)
        && subDiamond.stream().allMatch(line -> Stream.of(line.strip().toCharArray()).count() == 1);
  }


  @Property
  boolean should_be_symmetric_horizontally(@ForAll @UpperChars char letter) {

    String diamond = Diamond.print(letter);

    String[] splitDiamond = diamond.split("\n");
    int middleLine = letter - 'A';
    var upper = Arrays.asList(splitDiamond).subList(0, middleLine);
    var lower = Arrays.asList(splitDiamond).subList(middleLine + 1, splitDiamond.length);
    return upper.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()).equals(lower);
  }


  @Property
  boolean should_contain_one_A_on_first_and_last_line(@ForAll @UpperChars char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = diamond.split("\n");
    return splitDiamond[0].strip().equals("A") && splitDiamond[splitDiamond.length - 1].strip().equals("A");
  }

  @Property
  boolean should_contain_decreasing_numer_of_spaces_before_letter(@ForAll @UpperChars char letter) {
    return false;
  }

  @Provide
  Arbitrary<Character> upperLettersWithoutA() {
    return Arbitraries.chars().range('B', 'Z');
  }

  @Provide
  Arbitrary<Character> noneLetterChar() {
    return Arbitraries.chars().filter(character -> (character < 'A' || character > 'Z') && (character < 'a' || character > 'z'));
  }

  @Property
  boolean should_be_the_same_for_upper_and_lower_case_of_the_same_letter(@ForAll("upperLettersWithoutA") char letter) {
    return Diamond.print(letter).equals(Diamond.print(toLowerCase(letter)));
  }

  private static char toLowerCase(char letter) {
    return String.valueOf(letter).toLowerCase(Locale.ROOT).charAt(0);
  }


}