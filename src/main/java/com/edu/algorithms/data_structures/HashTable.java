package com.edu.algorithms.data_structures;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

public class HashTable<T> {
    private LinkedList<T>[] buckets;

    public int size() {
        return size;
    }

    private int size;

    public HashTable() {
        int INITIAL_CAPACITY = 16;
        this.buckets = new LinkedList[INITIAL_CAPACITY];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean add(T data) {
        int idx = getIdx(data);
        if (buckets[idx] == null) {
            buckets[idx] = new LinkedList<>();
        }
        if (!contains(data)) {
            buckets[idx].add(data);
            size++;
            return true;
        }
        return false;
    }

    public boolean contains(T data) {
        int idx = getIdx(data);
        if (idx < size) {
            LinkedList<T> bucket = buckets[idx];
            for (T t : bucket) {
                if (t.equals(data)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getIdx(T data) {
        int hashCode = Objects.hashCode(data);
        return Math.abs(hashCode % buckets.length);
    }

    public boolean remove(T el) {
        if (size > 0) {
            int idx = getIdx(el);
            LinkedList<T> bucket = buckets[idx];
            for (T t : bucket) {
                if (t.equals(el)) {
                    bucket.remove(t);
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.stream(buckets)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}

class Test {
    public static void main(String[] args) {
        HashTable<String> ht = new HashTable<>();
        ht.add("Hello");
        ht.add("world");
        ht.add("world");
        ht.add("world");
        ht.add("!");
        System.out.println(ht.size());
        System.out.println(ht);

        ht.remove("world");
        System.out.println(ht.size());
        System.out.println(ht);

        System.out.println(ht.contains("12313sqsada"));
    }
}
