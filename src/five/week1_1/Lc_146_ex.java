package five.week1_1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Lc_146_ex {
    class LRUCache {
//        int cap = 0;
//
//        public LRUCache(int capacity) {
//            super(capacity, 0.75F, true);
//            this.cap = capacity;
//
//        }
//
//        @Override
//        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//            return size() > cap;
//        }
//
//        public int get(int key) {
//            return super.getOrDefault(key, -1);
//        }
//
//        public void put(int key, int value) {
//            super.put(key, value);
//        }

        class Node {
            int key, value;
            Node prev, next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        class DoubleLinkedList {
            Node head;
            Node tail;
            int size = 0;

            public DoubleLinkedList() {
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
                size = 0;
            }

            //h 1 2 3 t
            void addFirst(Node x) {
                head.next.prev = x;
                x.next = head.next;
                x.prev = head;
                head.next = x;
                size++;
            }

            void remove(Node x) {
                x.prev.next = x.next;
                x.next.prev = x.prev;
                size--;
            }

            Node removeLast() {
                if (tail.prev == head) return null;
                Node x = tail.prev;
                remove(x);
                return x;
            }

            int size() {
                return size;
            }
        }

        int cap = 0;
        private Map<Integer, Node> map;
        private DoubleLinkedList cache;

        public LRUCache(int capacity) {
            this.cap = capacity;
            cache = new DoubleLinkedList();
            map = new HashMap<>();
        }

        public int get(int key) {

            if (map.containsKey(key)) {
                int v = map.get(key).value;
                put(key, v);
                return v;
            }
            return -1;
        }

        public void put(int key, int value) {
            Node node = new Node(key, value);
            if (map.containsKey(key)) {
                cache.remove(map.get(key));
                cache.addFirst(node);
                map.put(key, node);
                return;
            } else {
                if (cache.size() == cap) {
                    Node lastNode = cache.removeLast();
                    map.remove(lastNode.key);
                }
                cache.addFirst(node);
                map.put(key,node);

            }
        }
    }
}
