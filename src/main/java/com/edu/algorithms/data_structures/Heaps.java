package com.edu.algorithms.data_structures;

import java.util.Arrays;
import java.util.Optional;

public class Heaps {
    public static void main(String[] args) {
        var minHeap = new AwesomeIntegerMinHeap();
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(1);
        minHeap.add(18);
        System.out.println(minHeap.remove());
        System.out.println(minHeap.remove());
        System.out.println(minHeap.remove());
        System.out.println(minHeap.remove());
//        System.out.println(minHeap.remove());

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

class AwesomeIntegerMinHeap {
    private Integer[] arr;
    private int size;
    private static final int INITIAL_CAPACITY = 16;

    public AwesomeIntegerMinHeap() {
        arr = new Integer[INITIAL_CAPACITY];
    }

    public Integer peek() {
        return arr[0];
    }

    public void add(Integer data) {
        if (size == arr.length) {
            grow();
        }
        decreaseKey(size++, data);
    }

    public Integer remove() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty!");
        }
        Integer min = peek();
        arr[0] = arr[size--];
        minHeapify(0);
        return min;
    }

    private void grow() {
        arr = Arrays.copyOf(arr, (int) (size * 1.5));
    }

    private void minHeapify(int idx) {
        int smallest;
        int leftIdx = left(idx);
        if (leftIdx < size && Optional.ofNullable(arr[leftIdx]).orElse(Integer.MAX_VALUE) < Optional.ofNullable(arr[idx]).orElse(Integer.MAX_VALUE)) {
            smallest = leftIdx;
        } else {
            smallest = idx;
        }
        int rightIdx = right(idx);
        if (rightIdx < size && Optional.ofNullable(arr[rightIdx]).orElse(Integer.MAX_VALUE) < Optional.ofNullable(arr[smallest]).orElse(Integer.MAX_VALUE)) {
            smallest = rightIdx;
        }
        if (smallest != idx) {
            swapElements(idx, smallest);
            minHeapify(smallest);
        }

    }

    private void decreaseKey(int idx, Integer newVal) {
        if (arr[idx] != null && newVal > arr[idx]) {
            throw new RuntimeException("New value cannot be higher!");
        }
        arr[idx] = newVal;
        while (idx > 0 && arr[parent(idx)] > arr[idx]) {
            swapElements(parent(idx), idx);
            idx = parent(idx);
        }
    }

    private void swapElements(int firstIdx, int secondIdx) {
        Integer temp = arr[firstIdx];
        arr[firstIdx] = arr[secondIdx];
        arr[secondIdx] = temp;
    }

    private int left(int idx) {
        return 2 * idx + 1;
    }

    private int right(int idx) {
        return 2 * idx + 2;
    }

    private int parent(int idx) {
        return (int) Math.floor(((double) idx) / 2);
    }
}
