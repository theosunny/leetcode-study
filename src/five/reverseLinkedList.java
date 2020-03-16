package five;

public class reverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode prv = null;
        ListNode curr = head;
        while (curr!=null){
            ListNode next = curr.next;
            curr.next = prv;
            prv = curr;
            curr = next;
        }
        return prv;
    }
     public ListNode reverseListRecurse(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
         ListNode listNode = reverseListRecurse(head.next);
        head.next.next = head;
        head.next=null;
         return listNode ;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
