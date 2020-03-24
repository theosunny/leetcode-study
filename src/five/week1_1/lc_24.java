package five.week1_1;

import java.awt.*;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class lc_24 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 1->2->3->4, 你应该返回 2->1->4->3.
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            prev.next = second;
            first.next = second.next;
            second.next = first;
            prev = second;
            head = second.next;
        }
        return dummy.next;
    }

    //1,2,3,4
    public ListNode swapPairsOrigin(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = head; //1
        ListNode new_start = p.next;//2
        while (true) {
            ListNode q = p.next;//2
            ListNode temp = q.next;//3
            q.next = p;//2->1
            if (temp == null || temp.next == null) {
                p.next = temp;
                break;
            }
            //3->4
            p.next = temp.next;
            //
            p = temp;
        }
        return new_start;
    }

    public ListNode swapPairsRecurse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head;
        ListNode second = head.next;
        //swap
        first.next = swapPairsRecurse(second.next);
        second.next = first;
        return second;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        ListNode listNode = new lc_24().swapPairs(n1);
        System.out.println(listNode);
    }
}
