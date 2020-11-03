package algorithums.leetCode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class TestMain {
    private static Set<Integer> exists = new HashSet();
    public static void main(String args[]){

    }

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }

        while(head != null){
            if(!exists.add(head.val)){
                return false;
            }
            head = head.next;
        }
        return true;
    }
}
