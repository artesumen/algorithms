package com.edu.algorithms.data_structures;

import java.util.Optional;

public class Heaps {
    public static void main(String[] args) {
        AwesomeIntegerMaxHeap maxHeap = new AwesomeIntegerMaxHeap();
        maxHeap.add(1);
        maxHeap.add(4);
        maxHeap.add(2);
        maxHeap.add(3);

        System.out.println(maxHeap.remove());
        System.out.println(maxHeap.remove());
        System.out.println(maxHeap.remove());
        System.out.println(maxHeap.remove());
        System.out.println(maxHeap.remove());
    }

}

class AwesomeIntegerMaxHeap {
    private final Integer[] dataArray;
    private int size;
    private static final int CAPACITY = 10;

    public AwesomeIntegerMaxHeap() {
        dataArray = new Integer[CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    private void maxHeapify(int idx) {
        int largest;
        int left = left(idx);
        if (left < size &&
                Optional.ofNullable(dataArray[left]).orElse(Integer.MIN_VALUE) > Optional.ofNullable(dataArray[idx]).orElse(Integer.MIN_VALUE)) {
            largest = left;
        } else {
            largest = idx;
        }
        int right = right(idx);
        if (right < size &&
                Optional.ofNullable(dataArray[right]).orElse(Integer.MIN_VALUE) > Optional.ofNullable(dataArray[largest]).orElse(Integer.MIN_VALUE)) {
            largest = right;
        }
        if (largest != idx) {
            Integer temp = dataArray[idx];
            dataArray[idx] = dataArray[largest];
            dataArray[largest] = temp;
            maxHeapify(largest);
        }
    }

    private void increaseKey(int idx, int newVal) {
        if (dataArray[idx] != null && newVal < dataArray[idx]) {
            throw new RuntimeException("New value cannot be lower");
        }
        dataArray[idx] = newVal;
        while (idx > 0 && dataArray[parent(idx)] < dataArray[idx]) {
            int temp = dataArray[idx];
            dataArray[idx] = dataArray[parent(idx)];
            dataArray[parent(idx)] = temp;
            idx = parent(idx);
        }
    }

    public void add(Integer val) {
        size++;
        increaseKey(size - 1, val);
    }

    public Integer peek() {
        return dataArray[0];
    }

    public Integer remove() {
        if (size < 1) {
            throw new RuntimeException("Underflow");
        }
        Integer max = peek();
        dataArray[0] = dataArray[size--];
        maxHeapify(0);
        return max;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int parent(int idx) {
        return (int) Math.floor(((double) idx) / 2);
    }

    private int right(int i) {
        return 2 * i + 2;
    }
}
