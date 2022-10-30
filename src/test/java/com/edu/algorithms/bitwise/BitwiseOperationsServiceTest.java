package com.edu.algorithms.bitwise;

import com.edu.algorithms.bitwise.BitwiseOperationsService.Rect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class BitwiseOperationsServiceTest {

    @Autowired
    private BitwiseOperationsService bitwiseOperationsService;
    private static final int BINARY_NUMBER_0 = 0b0001_0011_0010_1110;
    private static final int BINARY_NUMBER_1 = 0b0001_0011_0010_1111;
    private static final int BINARY_NUMBER_1_CLOSEST = 0b0001_0011_0011_0111;
    private static final int BINARY_NUMBER_1_SWAPPED = 0b0000_1011_0010_1111;

    private static final int BINARY_NUMBER_1_REVERSED = Integer.reverse(BINARY_NUMBER_1);

    private static final Rect INITIAL_RECT = new Rect(1, 2, 3, 2);
    private static final Rect RECTANGLE_WITH_INTERSECTION = new Rect(2, 2, 2, 4);
    private static final Rect INTERSECTION_RECTANGLE = new Rect(2, 2, 2, 2);
    private static final Rect RECTANGLE_WITHOUT_INTERSECTION = new Rect(5, 2, 5, 4);
    private static final Rect INTERSECTION_ABSCENCE = new Rect(0, 0, -1, -1);

    @Test
    void suboptimalCountBits() {
        Assertions.assertEquals(8, bitwiseOperationsService.suboptimalCountBits(BINARY_NUMBER_1));
    }

    @Test
    void clearLowermostBit() {
        Assertions.assertEquals(BINARY_NUMBER_0, bitwiseOperationsService.clearLowermostBit(BINARY_NUMBER_1));
    }

    @Test
    void showBitOnThePosition() {
        System.out.println(1 << (5 - 1)); // 10000(2), 16(10), 5я позиция
        Assertions.assertTrue(bitwiseOperationsService.showBitOnThePosition(BINARY_NUMBER_0, 2));
        Assertions.assertFalse(bitwiseOperationsService.showBitOnThePosition(BINARY_NUMBER_0, 1));
    }

    @Test
    void optimalCountBits() {
        Assertions.assertEquals(8, bitwiseOperationsService.optimalCountBits(BINARY_NUMBER_1));
    }

    @Test
    void parityLooped() {
        Assertions.assertEquals(1, bitwiseOperationsService.parityLooped(BINARY_NUMBER_0));
        Assertions.assertEquals(0, bitwiseOperationsService.parityLooped(BINARY_NUMBER_1));
    }

    @Test
    void parity() {
        Assertions.assertEquals(1, bitwiseOperationsService.parity(BINARY_NUMBER_0));
        Assertions.assertEquals(0, bitwiseOperationsService.parity(BINARY_NUMBER_1));
    }

    @Test
    void swapBits() {
        Assertions.assertEquals(BINARY_NUMBER_1_SWAPPED, bitwiseOperationsService.swapBits(BINARY_NUMBER_1, 11, 12));
    }

    @Test
    void reverseBits() {
        Assertions.assertEquals(BINARY_NUMBER_1_REVERSED, bitwiseOperationsService.reverseBits(BINARY_NUMBER_1));
    }

    @Test
    void getClosestIntSameBitCount() {
        Assertions.assertEquals(BINARY_NUMBER_1_CLOSEST, bitwiseOperationsService.getClosestIntSameBitCount(BINARY_NUMBER_1));
    }

    @Test
    void multiply() {
        Assertions.assertEquals(25, bitwiseOperationsService.multiply(5, 5));
        Assertions.assertEquals(625, bitwiseOperationsService.multiply(25, 25));
    }

    @Test
    void divide() {
        Assertions.assertEquals(5, bitwiseOperationsService.divide(25, 5));
        Assertions.assertEquals(50, bitwiseOperationsService.divide(250, 5));
    }

    @Test
    void power() {
        Assertions.assertEquals(625.0, bitwiseOperationsService.power(25.0, 2));
        Assertions.assertEquals(-9.372983174258334, bitwiseOperationsService.power(-1.5645, 5));
    }

    @Test
    void reverse() {
        Assertions.assertEquals(12345, bitwiseOperationsService.reverse(54321));
    }

    @Test
    void isPalindrome() {
        Assertions.assertTrue(bitwiseOperationsService.isPalindrome(123321));
        Assertions.assertFalse(bitwiseOperationsService.isPalindrome(-123321));
        Assertions.assertFalse(bitwiseOperationsService.isPalindrome(123421));
    }

    @Test
    void uniformRandom() {
        List<Integer> integerList = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        for (int i = 0; i < 20; i++) {
            Assertions.assertTrue(integerList.contains(bitwiseOperationsService.uniformRandom(1, 9)));
        }
    }

    @Test
    void intersectRectangle() {
        Assertions.assertEquals(INTERSECTION_RECTANGLE, bitwiseOperationsService.intersectRectangle(INITIAL_RECT, RECTANGLE_WITH_INTERSECTION));
        Assertions.assertEquals(INTERSECTION_ABSCENCE, bitwiseOperationsService.intersectRectangle(INITIAL_RECT, RECTANGLE_WITHOUT_INTERSECTION));
    }
}
