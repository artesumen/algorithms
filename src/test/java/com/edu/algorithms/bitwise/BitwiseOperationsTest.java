package com.edu.algorithms.bitwise;

import com.edu.algorithms.bitwise.BitwiseOperations.Rect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BitwiseOperationsTest {

    @Autowired
    private BitwiseOperations bitwiseOperations;
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
        assertEquals(8, bitwiseOperations.suboptimalCountBits(BINARY_NUMBER_1));
    }

    @Test
    void clearLowermostBit() {
        assertEquals(BINARY_NUMBER_0, bitwiseOperations.clearLowermostBit(BINARY_NUMBER_1));
    }

    @Test
    void showBitOnThePosition() {
        System.out.println(1 << (5 - 1)); // 10000(2), 16(10), 5я позиция
        assertTrue(bitwiseOperations.showBitOnThePosition(BINARY_NUMBER_0, 2));
        assertFalse(bitwiseOperations.showBitOnThePosition(BINARY_NUMBER_0, 1));
    }

    @Test
    void optimalCountBits() {
        assertEquals(8, bitwiseOperations.optimalCountBits(BINARY_NUMBER_1));
    }

    @Test
    void parityLooped() {
        assertEquals(1, bitwiseOperations.parityLooped(BINARY_NUMBER_0));
        assertEquals(0, bitwiseOperations.parityLooped(BINARY_NUMBER_1));
    }

    @Test
    void parity() {
        assertEquals(1, bitwiseOperations.parity(BINARY_NUMBER_0));
        assertEquals(0, bitwiseOperations.parity(BINARY_NUMBER_1));
    }

    @Test
    void swapBits() {
        assertEquals(BINARY_NUMBER_1_SWAPPED, bitwiseOperations.swapBits(BINARY_NUMBER_1, 11, 12));
    }

    @Test
    void reverseBits() {
        assertEquals(BINARY_NUMBER_1_REVERSED, bitwiseOperations.reverseBits(BINARY_NUMBER_1));
    }

    @Test
    void getClosestIntSameBitCount() {
        assertEquals(BINARY_NUMBER_1_CLOSEST, bitwiseOperations.getClosestIntSameBitCount(BINARY_NUMBER_1));
    }

    @Test
    void multiply() {
        assertEquals(25, bitwiseOperations.multiply(5, 5));
        assertEquals(625, bitwiseOperations.multiply(25, 25));
    }

    @Test
    void divide() {
        assertEquals(5, bitwiseOperations.divide(25, 5));
        assertEquals(50, bitwiseOperations.divide(250, 5));
    }

    @Test
    void power() {
        assertEquals(625.0, bitwiseOperations.power(25.0, 2));
        assertEquals(-9.372983174258334, bitwiseOperations.power(-1.5645, 5));
    }

    @Test
    void reverse() {
        assertEquals(12345, bitwiseOperations.reverse(54321));
    }

    @Test
    void isPalindrome() {
        assertTrue(bitwiseOperations.isPalindrome(123321));
        assertFalse(bitwiseOperations.isPalindrome(-123321));
        assertFalse(bitwiseOperations.isPalindrome(123421));
    }

    @Test
    void uniformRandom() {
        List<Integer> integerList = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        for (int i = 0; i < 20; i++) {
            assertTrue(integerList.contains(bitwiseOperations.uniformRandom(1, 9)));
        }
    }

    @Test
    void intersectRectangle() {
        assertEquals(INTERSECTION_RECTANGLE, bitwiseOperations.intersectRectangle(INITIAL_RECT, RECTANGLE_WITH_INTERSECTION));
        assertEquals(INTERSECTION_ABSCENCE, bitwiseOperations.intersectRectangle(INITIAL_RECT, RECTANGLE_WITHOUT_INTERSECTION));
    }
}
