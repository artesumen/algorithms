package com.edu.algorithms;

import org.springframework.stereotype.Component;

@Component
public class Strings {

    public String intToString(int x) {
        boolean isNegative = x < 0;
        StringBuilder sb = new StringBuilder();

        do {
            sb.append('0' + Math.abs(x % 10));
            x /= 10;
        }
        while (x != 0);

        return isNegative ? sb.append('-').reverse().toString() : sb.reverse().toString();
    }

    public int stringToInt(String n) {
        return (n.charAt(0) == '-' ? -1 : 1) * n.substring((n.charAt(0) == '-' || n.charAt(0) == '+') ? 1 : 0)
                .chars()
                .reduce(0, (runningSum, c) -> runningSum * 10 + c - '0');
    }
}
