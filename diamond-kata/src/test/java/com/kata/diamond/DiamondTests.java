package com.kata.diamond;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiamondTests {
    @Test
    void test() {
        assertThat("Diamond")
                .startsWith("D")
                .endsWith("d");
    }
}