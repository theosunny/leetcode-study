package five;

/***
 *给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 *
 * 示例 2：
 *
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 *
 * 示例 3：
 *
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 *
 *
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 */
public class linkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        //method1()
        //method2() 龟兔赛跑找环
        return method2(head);
    }

    public ListNode method2(ListNode head) {
        if (head==null||head.next==null)return null;
        ListNode joinNode = hasCycle(head);
        if (joinNode==null)return null;
        ListNode curr = head;
        while (joinNode!=curr){
            joinNode = joinNode.next;
            curr = curr.next;
        }
        return curr;
    }

    public ListNode hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null || fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast==slow)return slow;
        }
        return null;

    }

    /**
     * 解法1标记法
     *
     * @param head
     * @return
     */

    public ListNode method1(ListNode head) {
        if (head == null || head.next == null) return null;
        if (head.val == 0xcafebabe) {
            return head;
        }
        head.val = 0xcafebabe;
        return method1(head.next);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        //[3,2,0,-4]
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;
        ListNode listNode = new linkedListCycleII().detectCycle(n1);
        System.out.println(listNode);
    }
}