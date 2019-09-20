package algorithm;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    int capacity, count;
    Map<Integer, Node> map;//用于快速获得数据，存放的是key，和实际的数据，其实没必要用key，直接就一个实际值就行了
    Node head, tail;//双向链表便于删除添加（因为可以很快获得前后节点）
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.pre = this.head;
        this.count = 0;
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(1);
        lru.put(2, 1);
        System.out.println(lru.get(2));
        lru.put(3, 2);
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            node = del(node);
            addToHead(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            //存在
            node.value = value;
            node = del(node);
            addToHead(node);
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            if (count == capacity) {
                map.remove(tail.pre.key);
                del(tail.pre);
            } else {
                count++;
            }
            addToHead(newNode);

        }
    }

    private Node del(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
        return node;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    class Node {
        int key;
        int value;
        Node pre;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }
}