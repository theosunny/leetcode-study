package week1.lesson3;

import java.util.HashSet;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 */
public class LinkedListCycle {
    //slow and fast pointer;
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode f = head;
        ListNode s = head.next;
        //choose fast and slow pointers
        while (s != f) {
            if (f == null || f.next == null) {
                return false;
            }
            s = s.next;
            f = f.next.next;
        }
        return true;
    }

    //hash
    public boolean hasCycleHash(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    //mark recurse
    public boolean hasCycleMark(ListNode head) {
        if (head == null || head.next == null) return false;
        if (head.val == 0xcafebabe) {
            return true;
        }
        head.val = 0xcafebabe;
        return hasCycle(head.next);
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            System.out.println(val);
            while (next != null) {
                System.out.println(next.val);
                next = next.next;
            }
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;
        boolean listNode = new LinkedListCycle().hasCycleMark(n1);
        System.out.println(listNode);
    }
}
