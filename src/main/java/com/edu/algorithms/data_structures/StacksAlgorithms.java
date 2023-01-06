package com.edu.algorithms.data_structures;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StacksAlgorithms {
    public static void main(String[] args) {
        AwesomeStack<String> s = new AwesomeStack<>();
        for (int i = 0; i < 10; i++) {
            s.push("Adding: " + i + " ");
        }
        System.out.println(s);
        System.out.println();
        System.out.println("-------------");
        for (int i = 0; i < 5; i++) {
            s.pop();
        }
        System.out.println(s);
        var stack = new ArrayDequeStackWithMax();
        for (int i = 1; i <= 3; i++) {
            stack.push(i);
        }
        stack.push(2);
        System.out.println(stack.max());
        stack.pop();
        stack.pop();
        System.out.println(stack.max());

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

    public Integer pop(){
        return arr.removeFirst().element;
    }
}
