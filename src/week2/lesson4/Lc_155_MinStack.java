package week2.lesson4;

/**
 * https://leetcode-cn.com/problems/min-stack/
 * 155. 最小栈
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 */
public class Lc_155_MinStack {
    class MinStack {
        Node head;

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            if (head == null) head = new Node(x, x);
            else head = new Node(x, Math.min(x, head.val), head);
        }

        public void pop() {
            if (head == null) return;
            head = head.next;
        }

        public int top() {
            if (head == null) return -1;
            return head.val;
        }

        public int getMin() {
            if (head == null) return -1;
            return head.min;
        }
    }

    class Node {
        int val;
        int min;
        Node next;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
            this.next = null;
        }

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    public static void main(String[] args) {
        MinStack obj = new Lc_155_MinStack().new MinStack();
        obj.push(1);
        obj.push(3);
        obj.push(2);
        obj.pop();
        int param_3 = obj.top();
        int param_4 = obj.getMin();
        System.out.println(param_3);
        System.out.println(param_4);
    }
}
