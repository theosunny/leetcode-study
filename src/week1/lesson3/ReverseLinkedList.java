package week1.lesson3;

/**
 * 206. 反转链表
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>     p  c
 * 输入: null->1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * iterator desc:
 * first: null as first node
 * second: p->null ;c->1
 * c->next=p   => 1->null;
 * p= curr;
 * move right one step
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        //iterator
        if (head == null || head.next == null) {
            return head;
        }
        //前指针节点
        ListNode prev = null;
        //当前指针节点
        ListNode curr = head;
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while (curr != null) {
            ListNode next = curr.next;//临时节点，暂存当前节点的下一节点用于翻
            curr.next = prev; //将当前节点指向它前面的节点
            prev = curr; //前指针后移
            curr = next;
        }
        return prev;

    }

    public ListNode reverseListRecurse(ListNode head) {
        if (head != null && head.next != null) return head;
        ListNode p = reverseListRecurse(head.next);
        head.next.next = head;
        head.next = null;
        return p;

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
        ListNode listNode = new ReverseLinkedList().reverseList(n1);
        listNode.toString();
    }

}
