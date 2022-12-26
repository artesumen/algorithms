package com.edu.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StringsTest {
    @Autowired
    private Strings strings;

    @Test
    void intToString() {
        Assertions.assertEquals("12345",strings.intToString(12345));
        Assertions.assertEquals("-12345",strings.intToString(-12345));
        Assertions.assertEquals("0",strings.intToString(0));
    }

    @Test
    void stringToInt() {
        Assertions.assertEquals(12345, strings.stringToInt("12345"));
    }
}