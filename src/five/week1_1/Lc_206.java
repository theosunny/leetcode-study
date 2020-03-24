package five.week1_1;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 206. 反转链表
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class Lc_206 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        return method1(head);
    }

    //额外数组暂时不考虑，没意思
    private ListNode method1(ListNode head) {
        return null;
    }

    //       p  c
    //循环   n  1 2 3
    private ListNode method2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    //递归
    private ListNode method3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = method3(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        ListNode listNode = new Lc_206().method3(n1);
        System.out.println(listNode);
    }
}
