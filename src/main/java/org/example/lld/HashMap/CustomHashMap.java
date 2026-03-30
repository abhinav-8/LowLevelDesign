package org.example.lld.HashMap;

import java.util.Arrays;

public class CustomHashMap <K,V>{
    private final int MAX_CAPACITY = 1<<30;
    private final int INITIAL_SIZE = 4;
    private final float LOAD_FACTOR = 0.75f;
    private int currentNodes=0;

    private Node[] map;

    public CustomHashMap() {
        map = new Node[INITIAL_SIZE];
        for(int i=0; i<INITIAL_SIZE; i++){
            map[i] = new Node<>(null, null);
            map[i].next = new Node<>(null, null);
            map[i].next.prev = map[i];
        }
    }

    private int getSize() {
        return currentNodes;
    }

    public void put(K key, V value) {
        Node<K,V> oldNode = findNode(key);
        if(oldNode != null) {
            oldNode.value = value;
            return;
        }

        int bucket = key.hashCode() % map.length;
        Node<K,V> head = map[bucket];
        Node<K,V> oldNext = head.next;
        head.next = new Node<K,V>(key, value);
        head.next.next = oldNext;
        oldNext.prev = head.next;
        currentNodes++;

        if(currentNodes > LOAD_FACTOR * map.length) {
            rehash(2 * map.length);
        }
    }


    public V get(K key){
        Node<K, V> node = findNode(key);
        if(node != null) return node.value;
        return null;
    }

    public Node<K,V> findNode(K key){
        int bucket = key.hashCode() % map.length;
        Node<K,V> node = map[bucket];

        while(node != null){
            if(node.key != null && node.key.equals(key)){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public void remove(K key){
        Node<K,V> node = findNode(key);
        if(node != null){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            currentNodes--;
            System.out.println("Node deleted: " + node);
            if(currentNodes <= LOAD_FACTOR * map.length/2) {
                rehash(map.length/2);
            }
            return;
        }
        System.out.println("Key not found");
    }

    public void rehash(int newSize){
        if(newSize > MAX_CAPACITY) {
            System.out.println("Map size limit reached");
            return;
        }

        System.out.println("Rehashing.......");

        Node[] newMap = new Node[newSize];
        for(int i=0; i<newSize; i++){
            newMap[i] = new Node<>(null, null);
            newMap[i].next = new Node<>(null, null);
            newMap[i].next.prev = newMap[i];
        }

        for (Node kvNode : map) {
            Node head = kvNode;
            while (head != null) {
                if (head.key != null) {
                    int bucket = head.key.hashCode() % newSize;
                    Node newMapHead = newMap[bucket];
                    Node newMapNext = newMapHead.next;
                    newMapHead.next = new Node(head.key, head.value);
                    newMapNext.prev = newMapHead.next;
                    newMapHead.next.next = newMapNext;
                    newMapHead.next.prev = newMapHead;
                }
                head = head.next;
            }
        }
        map = newMap;
    }

    public void showMap(){
        for(int i = 0; i < map.length; i++){
            Node kvNode = map[i];
            System.out.print("Bucket: " + i + " --> [");
            while(kvNode != null){
                if(kvNode.key != null){
                    System.out.print(kvNode);
                }
                kvNode = kvNode.next;
            }
            System.out.println("] ");
        }
        System.out.println("--------------------------");
    }
}
