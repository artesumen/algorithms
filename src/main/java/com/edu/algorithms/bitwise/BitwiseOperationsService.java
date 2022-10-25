package com.edu.algorithms.bitwise;

import org.springframework.stereotype.Service;

@Service
public class BitwiseOperationsService {

    /**
     * Counts the number of bits in integer
     * Time O(n); n - length of input
     *
     *  @param input integer arg
     * @return number of bits
     */
    public short suboptimalCountBits(int input) {
        short numOfBits = 0;
        while (input != 0) {
            numOfBits += input & 1; // Логическое "И" даст 1, если крайний правый бит = 1, иначе даст 0
            input >>>= 1; // Сдвиг всех битов на одну позицию вправо. Свободные позиции слева заменяем нулями
        }
        return numOfBits;
    }

    /**
     * Counts the number of bits in integer
     * Time O(k); n - number of bits ('1's) in input9
     *
     *  @param input integer arg
     * @return number of bits
     */
    public short optimalCountBits(int input){
        short result = 0;
        while(input != 0){
            input = clearLowermostBit(input);
            result++;
        }
        return result;
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

    /**
     * Counts the parity of the input
     * We can divide the counting by parts with XOR operations
     * Time O(log n); n - input size
     *
     * @param input integer arg
     * @return 0 if the nuber of bits is even, 1 otherwise
     */
    public short parityLooped(long input){
        for (int i = 32; i >=2 ; i/=2) {
            input ^= input >>> i;
        }
        return (short) (input & 0x1);
    }

    /**
     * Counts the parity of the input
     * We can divide the counting by parts with XOR operations
     * Time O(log n); n - input size
     *
     * @param input integer arg
     * @return 0 if the nuber of bits is even, 1 otherwise
     */
    private short parity(long input){
        input ^= input >>> 32;
        input ^= input >>> 16;
        input ^= input >>> 8;
        input ^= input >>> 4;
        input ^= input >>> 2;
        input ^= input >>> 1;
        return (short) (input & 0x1);
    }
}
