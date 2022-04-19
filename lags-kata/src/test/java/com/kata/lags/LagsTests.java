package com.kata.lags;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LagsTests {
    @Test
    void test() {
        assertThat("Lags")
                .startsWith("L");
    }
}