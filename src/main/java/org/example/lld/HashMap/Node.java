package org.example.lld.HashMap;

public class Node<K,V> {
    K key;
    V value;
    Node<K,V> next;
    Node<K,V> prev;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return "{Key: " + (key == null ? "null" : key.toString()) + ", Value: " + (value == null ? "null" : value.toString()) + "},";
    }
}
