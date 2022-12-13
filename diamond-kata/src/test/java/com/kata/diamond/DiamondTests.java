package com.kata.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
    String diamond = Diamond.print(letter);

    assertThat(diamond).isEmpty();

    return diamond.isEmpty();
  }

  @Property
  boolean should_contain_twice_the_letter_on_every_lines_except_on_first_and_last_line(@ForAll("upperLettersWithoutA") char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = splitDiamond(diamond);
    // Diamond without lines with letter 'A'
    var subDiamond = getDiamondPartList(splitDiamond, 1, splitDiamond.length - 1);

    return subDiamond.stream().allMatch(line -> line.strip().toCharArray().length == 2)
        && subDiamond.stream().allMatch(line -> Stream.of(line.strip().toCharArray()).count() == 1);
  }

  @Property
  boolean should_be_symmetric_horizontally(@ForAll @UpperChars char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = splitDiamond(diamond);
    int middleLine = getMiddleLine(letter);
    var upperDiamond = getDiamondPartList(splitDiamond, 0, middleLine);
    var lowerDiamond = getDiamondPartList(splitDiamond, middleLine + 1, splitDiamond.length);
    return upperDiamond.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()).equals(lowerDiamond);
  }

  @Property
  boolean should_contain_one_A_on_first_and_last_line(@ForAll @UpperChars char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = splitDiamond(diamond);
    var firstLineLetter = splitDiamond[0].strip();
    var lastLineLetter = splitDiamond[splitDiamond.length - 1].strip();
    return firstLineLetter.equals("A") && lastLineLetter.equals("A");
  }

  @Property
  boolean should_contain_decreasing_number_of_spaces_before_letters(@ForAll @UpperChars char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = splitDiamond(diamond);
    int middleLine = getMiddleLine(letter);
    var upperDiamondLeftSpaces = getUpperDiamond(splitDiamond, middleLine)
        .map(this::getCountSpaces)
        .collect(Collectors.toList());

    List<Integer> decreasingNumbersLeftSpaces = getDecreasingNumbers(middleLine);

    return upperDiamondLeftSpaces.equals(decreasingNumbersLeftSpaces);
  }

  @Property
  boolean should_contain_decreasing_number_of_spaces_after_letters(@ForAll @UpperChars char letter) {
    String diamond = Diamond.print(letter);

    String[] splitDiamond = splitDiamond(diamond);
    int middleLine = getMiddleLine(letter);
    var upperDiamondRightSpaces = getUpperDiamond(splitDiamond, middleLine)
        .map(this::getReverseCountSpaces)
        .collect(Collectors.toList());

    List<Integer> decreasingNumbersRightSpaces = getDecreasingNumbers(middleLine);

    return upperDiamondRightSpaces.equals(decreasingNumbersRightSpaces);
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

  private Stream<String> getUpperDiamond(String[] splitDiamond, int middleLine) {
    return getDiamondPartList(splitDiamond, 0, middleLine + 1).stream();
  }

  private List<String> getDiamondPartList(String[] splitDiamond, int from, int to) {
    return Arrays.asList(splitDiamond)
        .subList(from, to);
  }

  private int getMiddleLine(@ForAll @UpperChars char letter) {
    return letter - 'A';
  }

  private String[] splitDiamond(String diamond) {
    return diamond.split("\n");
  }

  private int getReverseCountSpaces(String line) {
    StringBuilder sb = new StringBuilder();
    sb.append(line);
    return getCountSpaces(sb.reverse().toString());
  }

  private int getCountSpaces(String line) {
    return (int) line
        .chars()
        .boxed()
        .takeWhile(c -> c == (int) ' ')
        .count();
  }

  private List<Integer> getDecreasingNumbers(int from) {
    return IntStream.rangeClosed(0, from)
        .boxed()
        .sorted(Collections.reverseOrder())
        .collect(Collectors.toList());
  }
}