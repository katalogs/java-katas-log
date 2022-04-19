package com.kata.roman;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToRomanNumeralsConverterTests {
    private final ToRomanNumeralsConverter converter = new ToRomanNumeralsConverter();

    @Test
    void should_be_empty_for_zero() {
        assertThat(converter.convert(0))
                .isBlank();
    }
}