package com.edu.algorithms.bitwise;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BitwiseOperationsService {

    /**
     * Counts the number of bits in integer
     * Time O(n); n - length of input
     *
     * @param input integer arg
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
     * Time O(k); n - number of bits ('1's) in input
     *
     * @param input integer arg
     * @return number of bits
     */
    public short optimalCountBits(int input) {
        short result = 0;
        while (input != 0) {
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
     * @param input    integer arg
     * @param position position to test
     * @return true if the bit was present on a position, false if not
     */
    public boolean showBitOnThePosition(int input, int position) {
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
    public short parityLooped(long input) {
        for (int i = 32; i >= 2; i /= 2) {
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
    public short parity(long input) {
        input ^= input >>> 32;
        input ^= input >>> 16;
        input ^= input >>> 8;
        input ^= input >>> 4;
        input ^= input >>> 2;
        input ^= input >>> 1;
        return (short) (input & 0x1);
    }

    /**
     * Swaps two bits in the ith and jth positions
     *
     * @param input 64-bit integer
     * @param i     1st bit to swap
     * @param j     2nd bit to swap
     * @return integer with swapped bits
     */
    public long swapBits(long input, int i, int j) {
        if (((input >>> i) & 1) != ((input >>> j) & 1)) { // Вытаскиваем биты на позициях i и j и сравниваем их
            long bitMask = (1L << i) | (1L << j); // Маска содержит биты на тех местах, которые надо поменять
            input ^= bitMask; // ХОР поменяет значение битов на противоположные
            System.out.println(Long.toBinaryString(input));
        }
        return input;
    }

    /**
     * Reverses bits in 32-bit integer
     *
     * @param input 32-bit integer
     * @return reversed result
     */
    //todo: Check optimal solution on Leetcode
    public int reverseBits(int input) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int lsb = input & 1; // Находим самый правый бит
            int temp = lsb << (31 - i); // двигаем бит на противоположную позицию (исходя из 32-битного входного числа)
            ans = ans | temp; //запоминаем в ans
            input = input >> 1; // Идем в следующий бит
        }
        System.out.println(Integer.toBinaryString(ans));
        return ans;
    }

    /**
     * Finds the integer closest to input with the same number of bits
     *
     * @param input 64-bit int
     * @return closest to input with the same number of bits
     */
    public long getClosestIntSameBitCount(long input) {
        final int NUM_UNSIGNED_BITS = 63;
        for (int i = 0; i < NUM_UNSIGNED_BITS - 1; i++) {
            if (((input >>> i) & 1) != ((input >>> i + 1) & 1)) { // Ищем два неравных соседних бита начиная с LSB
                input ^= (1L << i) | (1L << 1 + i); // Маска, с этими неравными битами
                return input;
            }
        }
        throw new RuntimeException("All bits are 1s or 0s");
    }

    /**
     * Multiplies 2 integers with bitwise operations
     * Addition takes O(n) time.
     * Thhe whole algorithm takes O(n^2)
     *
     * @param x 1st arg
     * @param y 2nd arg
     * @return product of x and y
     */
    public long multiply(long x, long y) {
        long res = 0;
        while (x != 0) {
            if ((x & 1) != 0) { // Проходимся по битам 1го числа. Если бит = 1 на позиции k, то прибавляем 2^k
                res = add(res, y);
            }
            x >>>= 1; // Двигаем вправо для итерирования побитно
            y <<= 1; // Двигаем влево, чтобы получить 2^k когда дойдем до 1 в 160й строке
        }
        return res;
    }

    private long add(long a, long b) {
        return b == 0 ? a : add(a ^ b, (a & b) << 1); // XOR даст сложение чисел без увеличения разряда. (a & b) << 1 увеличит разряд для дальнейшего сложения и его увеличения (carry)
    }

    /**
     * Divides x by y
     * O(n)
     *
     * @param x first arg
     * @param y second arg
     * @return quotient
     */
    public int divide(int x, int y) {
        int power = 32;
        long yPower = (long) y << power;
        int result = 0;
        while (x >= y) {
            while (yPower > x) {
                yPower >>>= 1;
                --power;
            }
            result += 1 << power;
            x -= yPower;
        }
        return result;
    }

    /**
     * Counts the x^y ignoring overflow
     * The number of multiplication is at most twice of MSB
     * O(n)
     *
     * @param x arg
     * @param y power
     * @return x^y
     */
    public double power(double x, int y) {
        double result = 1.0;
        long power = y;
        if (y < 0) {
            power = -power;
            x = 1.0 / x;
        }
        while (power != 0) {
            if ((power & 1) != 0) { //Проверяем на нечетность
                result *= x;
            }
            x *= x;
            power >>>= 1;
        }
        return result;
    }

    /**
     * Reverses the input integer
     * O(n), n - number of digits
     *
     * @param x 32-bit integer
     * @return reversed integer
     */
    public long reverse(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10; // Остаток от деления на 10 даст самую правую цифру в числе.Умножая на 10 мы двигаем существующее число выше на разряд
            x /= 10;
        }
        return result;
    }

    /**
     * Checks if the input number is a palindrome
     * O(n) time complexity
     * As alternative we can use @reverse(int x) for check
     *
     * @param input 32-bit integer
     * @return true if the input is a palindrome, false otherwise
     */
    public boolean isPalindrome(int input) {
        if (input <= 0) {
            return input == 0;
        }
        int numOfDigits = (int) (Math.floor(Math.log10(input)) + 1); // Считаем разрядность числа
        int msdMask = (int) Math.pow(10, numOfDigits - 1); // Маска, чтобы вычислять наибольший разряд (левую цифру числа)

        for (int i = 0; i < (numOfDigits / 2); i++) { // Итерируемся до середины
            if (input / msdMask != input % 10) { // Сверяем самый левый и самый правый разряд
                return false;
            }
            input %= msdMask; // Убираем бОльший разряд
            input /= 10; // Убираем меньший разряд
            msdMask /= 100; // Обновляем маску
        }
        return true;
    }

    /**
     * Generates random number in bounds inclusive
     * Uses random generator of 2 numbers
     * O(log(b-a+1)) time-
     *
     * @param lowerBound lower bound
     * @param upperBound upper bound
     * @return random number in range inclusive
     */
    public int uniformRandom(int lowerBound, int upperBound) {
        int numberOfOutcomes = upperBound - lowerBound + 1; // Количество чисел, в которые можем попасть
        int result;
        do {
            result = 0; // Сбрасываем в ноль, если вышли за рамки
            for (int i = 0; (1 << i) < numberOfOutcomes; i++) { // Сеттим биты слева направо случайными значениями из генератора
                result = (result << 1) | zeroOneRandom();
            }
        }
        while (result >= numberOfOutcomes);
        return result + lowerBound;
    }

    private int zeroOneRandom() {
        return new Random().nextInt(2);
    }

    @EqualsAndHashCode // !!!!!!!!!!
    @ToString
    public static class Rect {
        int x, y, width, height;

        public Rect(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    /**
     * Checks if two rectangles have a non-empty intersection
     * If yes, returns rectangle formed by this intersection
     * O(1), since the number of operations is constant
     *
     * @param r1 first rectangle
     * @param r2 second rectangle
     * @return intersection rectangle if there is any, Rect(0, 0, -1, -1) otherwise
     */
    public Rect intersectRectangle(Rect r1, Rect r2) {
        if (!isIntersect(r1, r2)) {
            return new Rect(0, 0, -1, -1); // Нет пересечений
        }
        return new Rect(Math.max(r1.x, r2.x), Math.max(r1.y, r2.y),
                Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x), // Math.min(r1.x + r1.width, r2.x + r2.width) Считает длину начиная от оси координат. ПОэтому мы вычитаем наибольшую координату, чтобы рассчитать длину меньшего прямоугольника
                Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y)); // Аналогично предыдущему
    }

    private boolean isIntersect(Rect r1, Rect r2) {
        return r1.x <= r2.x + r2.width && r1.x + r1.width >= r2.x &&
                r1.y <= r2.y + r2.height && r2.y <= r1.y + r1.height;  // Проверяем, что высота и ширина по координатам (не) пересекается
    }
}
