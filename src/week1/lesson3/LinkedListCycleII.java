package week1.lesson3;


/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 */
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode intersect = getIntersect(head);
        if (intersect == null) return null;
        ListNode h = head;
        ListNode i = intersect;
        while (h != i) {
            i = i.next;
            h = h.next;

        }
        return i;
    }

    public ListNode getIntersect(ListNode head) {
        ListNode slow = head;
        ListNode fs = head;
        while (fs != null && fs.next != null) {
            slow = slow.next;
            fs = fs.next.next;
            if (fs == slow) {
                return slow;
            }
        }
        return null;
    }

    public ListNode detectCycleMark(ListNode head) {
        if (head == null || head.next == null) return null;
        if (head.val == 0xcafebabe) {
            return head;
        }
        head.val = 0xcafebabe;
        return detectCycle(head.next);
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
        //[3,2,0,-4]
         ListNode n1 = new  ListNode(3);
         ListNode n2 = new  ListNode(2);
         ListNode n3 = new  ListNode(0);
         ListNode n4 = new  ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;
        ListNode listNode = new LinkedListCycleII().detectCycle(n1);
        System.out.println(listNode);
    }
}
