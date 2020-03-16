package week1.lesson3;


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
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        //iterator
        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        ListNode prev = dumy;
        while (head != null && head.next != null) {
            ListNode first = head; //1
            ListNode second = head.next; //2
            //swapping
            prev.next = second;
            first.next = second.next;
            second.next = first;
            // Reinitializing the head and prevNode for next swap
            prev = first;
            head = first.next;
        }
        return dumy.next;

    }

    public ListNode swapPairsIterator(ListNode head) {
        //virtual new dummy node as head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null && head.next != null) {
            //record two node for swap
            ListNode first = head;
            ListNode second = head.next;
            //swap node
            prev.next = second;
            first.next = second.next;
            second.next = first;

            //reinitialize node
            prev = first;
            head = first.next;
        }
        return dummy.next;

    }

    public ListNode swapPairsIteratorAgain(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            prev.next = second;
            first.next = second.next;
            second.next = first;
            //reinitialize node
            prev = first;
            head = first.next;
        }
        return dummy.next;
    }

    public ListNode swapPairsRecurse(ListNode head) {
        if (head == null || head.next == null) return head;
        // Nodes to be swapped
        ListNode first = head; //1
        ListNode second = head.next; //2
        // Swapping
        first.next = swapPairs(second.next);
        second.next = first;
        return second;

    }

    public ListNode swapPairsRecurseAgain(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head;
        ListNode second = head.next;
        first.next = swapPairsRecurseAgain(second.next);
        second.next = first;
        return second;
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
        n3.next = n4;
        ListNode listNode = new SwapNodesInPairs().swapPairs(n1);
        listNode.toString();
    }
}
