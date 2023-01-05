package com.edu.algorithms.data_structures;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class LinkedListsAlgorithms {

}

class AwesomeLinkedList<T> implements Iterable<T> {
    private ListNode<T> head, tail;
    public int size;

    public int getSize() {
        return size;
    }

    public AwesomeLinkedList() {
    }

    public void add(T data) {
        final ListNode<T> t = tail;
        ListNode<T> newListNode = new ListNode<>(null, data);
        tail = newListNode;
        if (t == null) {
            head = newListNode;
        } else {
            t.setNext(newListNode);
        }
        ++size;
    }

    public ListNode<T> searchNode(ListNode<T> L, T key) {
        while (L != null && !L.getData().equals(key)) {
            L = L.getNext();
        }
        return L;
    }

    public void remove(int index) {
        if (index < size) {
            if (index > 0) {
                var el = getNodeByIndex(index - 1);
                removeNextNode(el);
            } else {
                this.head = head.getNext();
            }
        }
        --size;
    }

    private void removeNextNode(ListNode<T> listNode) {
        listNode.setNext(listNode.getNext().getNext());
    }

    private ListNode<T> getNodeByIndex(int index) {
        if (index < size) {
            var node = this.head;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node;
        } else {
            throw new RuntimeException("Out of bounds!");
        }
    }

    public T get(int index) {
        return getNodeByIndex(index).getData();
    }

    public ListNode<T> getHead() {
        return head;
    }

    public void setHead(ListNode<T> head) {
        this.head = head;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(this);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (T t : this) {
            sb.append(t + " ");
        }
        return sb.toString();
    }
}

class ListNode<T> {
    private ListNode<T> next;
    private T data;

    public ListNode(ListNode<T> next, T data) {
        this.next = next;
        this.data = data;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
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
    private ListNode<T> current;

    public ListIterator(AwesomeLinkedList<T> list) {
        current = new ListNode<>(list.getHead(), null);
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
