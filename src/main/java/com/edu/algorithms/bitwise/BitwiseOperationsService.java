package com.edu.algorithms.bitwise;

import org.springframework.stereotype.Service;

@Service
public class BitwiseOperationsService {

    /**
     * Counts the number of bits in integer
     * Time O(n); n - number of bits in input
     *
     *  @param input integer arg
     * @return number of bits
     */
    public short countBits(int input) {
        short numOfBits = 0;
        while (input != 0) {
            numOfBits += input & 1; // Логическое "И" даст 1, если крайний правый бит = 1, иначе даст 0
            input >>>= 1; // Сдвиг всех битов на одну позицию вправо. Свободные позиции слева заменяем нулями
        }
        return numOfBits;
    }

    /**
     * Finds the lowermost bit (the rightest '1') in input. Sets to '0'
     *
     * @param input integer arg
     * @return Value with updated value of the lowermost bit
     */
    public int clearLowermostBit(int input) {
        return input & (input - 1);
    }

    /**
     * Builds a mask and gets a bit out of the input
     *
     * @param input integer arg
     * @param position position to test
     * @return true if the bit was present on a position, false if not
     */
    public boolean showBitOnThePosition(int input, int position){
        int mask = 1 << (position - 1); // Маска будет иметь единственный бит на указанной позиции
        return (input & mask) != 0;
    }
}
