package com.edu.algorithms.data_structures;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ArraysTest {

    @Autowired
    private ArraysAlgorithms arraysAlgorithms;

    @Test
    void evenOdd() {
        assertEquals(List.of(6, 2, 4, 5, 3, 1), arraysAlgorithms.evenOdd(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

}