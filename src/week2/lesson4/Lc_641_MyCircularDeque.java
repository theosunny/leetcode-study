package week2.lesson4;

import java.util.LinkedList;
import java.util.Stack;

public class Lc_641_MyCircularDeque {
}

/***
 * 641. 设计循环双端队列
 *
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 *
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 * 示例：
 *
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 *  前---后
 *  312
 *  431
 *
 * 提示：
 *
 * 所有值的范围为 [1, 1000]
 * 操作次数的范围为 [1, 1000]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-circular-deque
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 环形队列（循环队列）主要概念
 * 先进先出
 * capacity：数组容量
 * front：表示队列队首，始终指向队列中的第一个元素（当队列空时，front指向索引为0的位置）
 * tail：表示队列队尾，始终指向队列中的最后一个元素的下一个位置
 * 元素入队，维护tail的位置，进行tail++操作，计算公式：(tail + 1) % capacity
 * 元素出队，维护front的位置，进行front++操作，计算公式：(front + 1) % capacity
 * 在循环队列中，总是浪费一个空间，来区分队列为满时和队列为空时的情况，也就是当 (tail + 1) % capacity == front的时候，表示队列已经满了，当front == tail的时候，表示队列为空。
 *
 */
class MyCircularDeque {
    private int[] ring;

    private int capacity;
    //表示队列队首，始终指向队列中的第一个元素（当队列空时，front指向索引为0的位置）
    private int head;
    //表示队列队尾，始终指向队列中的最后一个元素的下一个位置
    private int tail;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        // 因为环形队列需要一个空位判断队列为空,否则队列满和队列空是一样的判断，加了1空格则可以以(tail+1)%capacity==head;
        capacity = k + 1;
        ring = new int[capacity];

    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        // 移动到前一位
        head = (head + capacity - 1) % capacity;
        ring[head] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        ring[tail] = value;
        tail = (tail + 1) % capacity;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        head = (head + 1) % capacity;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        tail = (tail + capacity - 1) % capacity; // 移动到前一位
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) return -1;
        return ring[head];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) return -1;
        return ring[(tail + capacity - 1) % capacity];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */
    public static void main(String[] args) {
        System.out.println(3%4);
        MyCircularDeque deque = new MyCircularDeque(4);
        deque.insertFront(1);
        deque.insertFront(2);
        deque.insertLast(3);
        deque.insertLast(4);
        deque.deleteFront();
        deque.getFront();
        deque.getRear();
    }

}
