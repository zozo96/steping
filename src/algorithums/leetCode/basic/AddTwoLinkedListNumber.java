package leetCode.basic;

public class AddTwoLinkedListNumber {
    private boolean plus1 = false;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        if (l1 != null && l2 != null){
            if (plus1){
                plus1 = (l1.val + l2.val + 1) > 9 ;
                node.val = (l1.val + l2.val + 1) % 10;
            } else {
                plus1 = (l1.val + l2.val) > 9 ;
                node.val = (l1.val + l2.val) % 10;
            }
            node.next = addTwoNumbers(l1.next, l2.next);
            return node;
        } else if (l1 == null && l2 == null){
            if (plus1){
                node.val = 1;
                return node;
            } else {
                return null;
            }
        } else {
            ListNode l = l1 == null? l2 : l1;
            int val = l1 == null? l2.val : l1.val;
            if (plus1){
                plus1 = val + 1 > 9;
                node.val = (val + 1) % 10;
            } else {
                node.val = val;
            }

            node.next = addTwoNumbers(l.next, null);
            return node;
        }
    }
}
