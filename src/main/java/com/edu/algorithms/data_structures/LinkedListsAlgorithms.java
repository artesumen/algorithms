package com.edu.algorithms.data_structures;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class LinkedListsAlgorithms {

}

class AwesomeLinkedList<T> implements Iterable<T> {
    private Node<T> head, tail;

    public AwesomeLinkedList() {
    }

    public void add(T data) {
        final Node<T> t = tail;
        Node<T> newNode = new Node<>(null, data);
        tail = newNode;
        if (t == null) {
            head = newNode;
        } else {
            t.setNext(newNode);
        }
    }

    public Node<T> searchNode(Node<T> L, T key){
        while(L != null && !L.getData().equals(key)){
            L = L.getNext();
        }
        return L;
    }

    public void removeNextNode(Node<T> node){
        node.setNext(node.getNext().getNext());
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(this);
    }
}

class Node<T> {
    private Node<T> next;
    private T data;

    public Node(Node<T> next, T data) {
        this.next = next;
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

class ListIterator<T> implements Iterator<T> {
    private Node<T> current;

    public ListIterator(AwesomeLinkedList<T> list) {
        current = new Node<>(list.getHead(),null);
    }

    @Override
    public boolean hasNext() {
        return current.getNext() != null;
    }

    @Override
    public T next() {
        T data = current.getNext().getData();
        current = current.getNext();
        return data;
    }
}
