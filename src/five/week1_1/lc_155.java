package five.week1_1;

/**
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
 * minStack.getMin();   --> 返回 -2.
 */
public class lc_155 {
}

class MinStack {
    class Node {
        int v;
        int min;
        Node next;

        public Node(int v, int min) {
            this.v = v;
            this.min = min;
        }

        public Node() {
        }
    }

    private Node head;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
            return;
        }
        int min = head.min;
        Node node = new Node(x, Math.min(min, x));
        node.next = head;
        head = node;
    }

    public void pop() {
        if (head == null) {
            return;
        }
        head = head.next;
    }

    public int top() {
        return head.v;
    }

    public int getMin() {
        return head.min;
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