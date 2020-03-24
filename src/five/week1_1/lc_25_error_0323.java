package five.week1_1;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给你这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * <p>
 * <p>
 * 说明：
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class lc_25_error_0323 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverseSingle(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    public ListNode reverseSingle(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    // ListNode dummy = new ListNode(-1);
//        dummy.next = head;
//        ListNode pre = dummy;
//        ListNode end = dummy;
//        while (end.next != null) {
//            for (int i = 0; i < k && end != null; i++) {
//                end = end.next;
//            }
//            if (end == null) break;
//            ListNode start = pre.next;
//            ListNode next = end.next;
//            end.next = null;
//            //as next loop start node
//            pre.next = reverseSingle(start);
//            start.next = next;
//            pre = start;
//            end = pre;
//
//        }
//        return dummy.next;
//
//    }
//
//    public ListNode reverseSingle(ListNode head) {
//        if (head == null || head.next == null) return head;
//        ListNode prev = null;
//        ListNode curr = head;
//        while (curr != null) {
//            ListNode next = curr.next;
//            curr.next = prev;
//            prev = curr;
//            curr = next;
//        }
//        return prev;
    public static void main(String[] args) {
        System.out.println(10 / 7);
        System.out.println(10 % 7);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        ListNode listNode = new lc_25_error_0323().reverseKGroup(n1, 2);
        System.out.println(listNode);
    }
}
