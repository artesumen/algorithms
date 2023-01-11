package com.edu.algorithms.data_structures;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StacksAlgorithms {
    public static void main(String[] args) {
        System.out.println(getViewedBuildings(Arrays.asList(3, 2, 3, 4, 1).iterator()));
        System.out.println(optimizePathname("./././Hello/.."));
    }

    public static List<Integer> getViewedBuildings(Iterator<Integer> sequence) {
        Deque<Building> candidates = new ArrayDeque<>();
        int idx = 0;
        while (sequence.hasNext()) {
            int currentHeight = sequence.next();
            while (!candidates.isEmpty() && currentHeight >= candidates.peek().height) {
                candidates.removeFirst();
            }
            candidates.addFirst(new Building(idx++, currentHeight));
        }
        return candidates.stream().map(b -> b.id).collect(Collectors.toList());
    }

    public static String optimizePathname(String pathname) {
        Deque<String> tokens = new ArrayDeque<>();
        if (pathname.startsWith("/")) {
            tokens.addFirst("/");
        }

        for (String token : pathname.split("/")) {
            if (token.equals("..")) {
                if(tokens.isEmpty() || tokens.peekFirst().equals("..")){
                    tokens.addFirst(token);
                }
                else{
                    if(tokens.peekFirst().equals("/")){
                        throw new RuntimeException("Cannot go up the root");
                    }
                    tokens.removeFirst();
                }
            }
            if (!token.equals(".") && !token.isEmpty()) {
                tokens.addFirst(token);
            }
        }

        Iterator<String> iter = tokens.descendingIterator();
        StringBuilder res = new StringBuilder();
        while (iter.hasNext()) {
            if (tokens.peekFirst().equals("/")) {
                res.append("/");
            }
            String next = iter.next();
            res.append(next);
            res.append("/");
        }
        return res.toString();
    }

}

class AwesomeStack<T> {
    private final Node<T> head = new Node<>(null, null);

    public AwesomeStack() {
    }

    public void push(T data) {
        var nodeToAdd = new Node<>(data, null);
        var second = this.head.next;
        this.head.next = nodeToAdd;
        nodeToAdd.next = second;
    }

    public T pop() {
        var upperNode = head.next;
        if (upperNode == null) {
            throw new RuntimeException("Stack is empty");
        }
        head.next = upperNode.next;
        return upperNode.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        var printer = head.next;
        if (printer == null) {
            return "Stack is empty";
        }
        while (printer != null) {
            sb.append(printer.data);
            printer = printer.next;
        }
        return sb.toString();
    }

}

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "null";
    }
}

class CachedMaxElement {
    Integer element;
    Integer max;

    public CachedMaxElement(Integer element, Integer max) {
        this.element = element;
        this.max = max;
    }
}

class ArrayDequeStackWithMax {
    private final Deque<CachedMaxElement> arr = new ArrayDeque<>();

    public boolean empty() {
        return arr.isEmpty();
    }

    public Integer max() {
        if (!empty()) {
            return Objects.requireNonNull(arr.peek()).max;
        } else {
            throw new RuntimeException("Empty Stack");
        }
    }

    public void push(Integer data) {
        CachedMaxElement e = new CachedMaxElement(data, Math.max(data, empty() ? data : max()));
        arr.addFirst(e);
    }

    public Integer pop() {
        return arr.removeFirst().element;
    }
}

class Building {
    int height;
    int id;

    public Building(int height, int id) {
        this.height = height;
        this.id = id;
    }
}
