package com.edu.algorithms.data_structures;

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
        var iter = head.next;
        if (iter == null) {
            return "Stack is empty";
        }
        while (iter != null) {
            sb.append(iter.data);
            iter = iter.next;
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
