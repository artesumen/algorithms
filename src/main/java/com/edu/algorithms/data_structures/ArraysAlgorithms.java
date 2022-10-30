package com.edu.algorithms.data_structures;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ArraysAlgorithms {

    /**
     * Reorders the input list such a way that the even entries appear first
     * O(n) time
     * O(1) memory
     *
     * @param input input list
     * @return reordered list
     */
    public List<Integer> evenOdd(List<Integer> input) {
        int nextEven = 0;
        int nextOdd = input.size() - 1;
        while(nextEven < nextOdd){
            if(input.get(nextEven)%2==0){
                nextEven++;
            } else{
                Collections.swap(input,nextEven,nextOdd--);
            }
        }
        return input;
    }
}
