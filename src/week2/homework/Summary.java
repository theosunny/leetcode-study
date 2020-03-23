
package week2.homework;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Summary {
        public static void main(String[] args) {
            Deque<String> deque = new LinkedList<>();
            deque.addFirst("A"); // A
            deque.addFirst("B"); // B -> A
            deque.addLast("C"); // B -> A -> C
            System.out.println(deque.pollFirst()); // B, 剩下 A ->C
            System.out.println(deque.pollLast()); // C,剩下A
            System.out.println(deque.pollFirst()); // A
            System.out.println(deque.pollFirst()); // null
        }
}
