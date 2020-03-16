package week1.lesson3;

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
public class reverseKGroup {
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
            //as next loop start node
            pre.next = reverseSingle(start);
            start.next = next;
            pre = start;
            end = pre;

        }
        return dummy.next;

    }

    public ListNode reverseKGroupAgain(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            //
            pre.next = reverseSingle(start);

            start.next = next;
            pre = start;
            end = pre;

        }
        return dummy.next;
    }

    //1,2
    public ListNode reverseSingle(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next; //2
            curr.next = prev;//1 next null ,head =1
            prev = curr;//prev = 1;
            curr = next;//curr = 2;
        }
        return prev;
    }

    public ListNode reverseKGroupAgainAgain(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode end = dummy;
        while (end != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = prev.next;
            ListNode next = end.next;
            end.next = null;
            prev.next = reverseSingleAgain(start);
            start.next = next;
            prev = start;
            end = prev;

        }
        return dummy.next;
    }

    public ListNode reverseSingleAgain(ListNode head) {
        if (head == null) return head;
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

//        @Override
//        public String toString() {
//            System.out.println(val);
//            while (next != null) {
//                System.out.println(next.val);
//                next = next.next;
//            }
//            return "ListNode{" +
//                    "val=" + val +
//                    ", next=" + next +
//                    '}';
//        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        ListNode listNode = new reverseKGroup().reverseKGroupAgain(n1, 3);
        listNode.toString();
    }
}
