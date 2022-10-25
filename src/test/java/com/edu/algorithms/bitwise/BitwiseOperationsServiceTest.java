package com.edu.algorithms.bitwise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BitwiseOperationsServiceTest {

    @Autowired
    private BitwiseOperationsService bitwiseOperationsService;
    private static final int BINARY_NUMBER_0 = 0b0001_0011_0010_1110;
    private static final int BINARY_NUMBER_1 = 0b0001_0011_0010_1111;

    @Test
    void suboptimalCountBits() {
        Assertions.assertEquals(8, bitwiseOperationsService.countBits(BINARY_NUMBER_1));
    }

    @Test
    void clearLowermostBit() {
        Assertions.assertEquals(BINARY_NUMBER_0,bitwiseOperationsService.clearLowermostBit(BINARY_NUMBER_1));
    }

    @Test
    void showBitOnThePosition() {
        System.out.println(1<<(5-1)); // 10000(2), 16(10), 5я позиция
        Assertions.assertTrue(bitwiseOperationsService.showBitOnThePosition(BINARY_NUMBER_0, 2));
        Assertions.assertFalse(bitwiseOperationsService.showBitOnThePosition(BINARY_NUMBER_0, 1));
    }
}