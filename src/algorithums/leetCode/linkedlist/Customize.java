package leetCode.linkedlist;

/**
 * 自定义双向链表
 *
 * @author Sonya
 * @date 2020/7/16 0:40
 */
public class Customize {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtIndex(0,10);   //链表变为1-> 2-> 3
        linkedList.addAtIndex(0,20);   //链表变为1-> 2-> 3
        linkedList.addAtIndex(1,30);   //链表变为1-> 2-> 3
        System.out.println(linkedList.get(0));            //返回2
    }
}

/**
 * https://leetcode-cn.com/problems/design-linked-list/
 */
class MyLinkedList {

    private int val;
    private MyLinkedList next;
    private MyLinkedList prev;

    public MyLinkedList() {
        this.next = null;
        this.prev = null;
        this.val = -1;
    }

    public MyLinkedList(MyLinkedList pre, MyLinkedList next, int val) {
        this.next = next;
        this.prev = pre;
        this.val = val;
    }

    public int get(int index) {
        MyLinkedList ptr = fetchIndex(index);
        if(ptr == null) return -1;
        return ptr.val;
    }

    public void addAtHead(int val) {
        MyLinkedList head = this.fetchHead();
        // head newFirst head.next
        MyLinkedList newFirst = new MyLinkedList(head, head.next, val);
        if (head.next != null) {
            head.next.prev = newFirst;
        }
        head.next = newFirst;
    }

    public void addAtTail(int val) {
        MyLinkedList tail = this.fetchTail();
        tail.next = new MyLinkedList(tail, null, val);
    }

    public void addAtIndex(int index, int val) {
        MyLinkedList ptr = fetchIndex(index);
        if(ptr == null){
            MyLinkedList pre = fetchIndex(index - 1);
            if (pre == null) return;
            pre.next = new MyLinkedList(pre, null, val);
            return;
        }
        MyLinkedList newNode = new MyLinkedList(ptr.prev, ptr, val);
        ptr.prev.next = newNode;
        ptr.prev = newNode;
    }

    public void deleteAtIndex(int index) {
        MyLinkedList ptr = this.fetchIndex(index);
        if(ptr == null) return;
        if (ptr.prev != null) {
            ptr.prev.next = ptr.next;
        }
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public boolean hasPre() {
        return this.prev != null && this.prev.val != -1;
    }

    public boolean isHead() {
        return this.prev == null && this.val == -1;
    }

    public boolean isTail() {
        return this.next == null;
    }

    public MyLinkedList fetchTail(){
        if(this.isTail()) {
            return this;
        }
        MyLinkedList ptr = this;
        while(ptr.next != null){
            ptr = ptr.next;
        }
        return ptr;
    }

    public MyLinkedList fetchHead(){
        if(this.isHead()) {
            return this;
        }
        MyLinkedList ptr = this;
        MyLinkedList next = ptr.next;
        while(ptr.hasPre()) {
            next = ptr;
            ptr = ptr.prev;
        }
        // head ptr next
        return ptr.prev;
    }

    public MyLinkedList fetchIndex(int index) {
        MyLinkedList ptr = this;
        for(int i = -1; i < index ; i++) {
            if(ptr.hasNext()) {
                MyLinkedList n = ptr.next;
                ptr = n;
            } else {
                return null;
            }
        }
        return ptr;
    }
}

//["MyLinkedList","addAtHead","addAtTail","addAtTail","addAtTail","addAtTail","addAtTail","addAtTail","deleteAtIndex","addAtHead","addAtHead","get","addAtTail","addAtHead","get","addAtTail","addAtIndex","addAtTail","addAtHead","addAtHead","addAtHead","get","addAtIndex","addAtHead","get","addAtHead","deleteAtIndex","addAtHead","addAtTail","addAtTail","addAtIndex","addAtTail","addAtHead","get","addAtTail","deleteAtIndex","addAtIndex","deleteAtIndex","addAtHead","addAtTail","addAtHead","addAtHead","addAtTail","addAtTail","get","get","addAtHead","addAtTail","addAtTail","addAtTail","addAtIndex","get","addAtHead","addAtIndex","addAtHead","addAtTail","addAtTail","addAtIndex","deleteAtIndex","addAtIndex","addAtHead","addAtHead","deleteAtIndex","addAtTail","deleteAtIndex","addAtIndex","addAtTail","addAtHead","get","addAtIndex","addAtTail","addAtHead","addAtHead","addAtHead","addAtHead","addAtHead","addAtHead","deleteAtIndex","get","get","addAtHead","get","addAtTail","addAtTail","addAtIndex","addA...
//        [[],[38],[66],[61],[76],[26],[37],[8],[5],[4],[45],[4],[85],[37],[5],[93],[10,23],[21],[52],[15],[47],[12],[6,24],[64],[4],[31],[6],[40],[17],[15],[19,2],[11],[86],[17],[55],[15],[14,95],[22],[66],[95],[8],[47],[23],[39],[30],[27],[0],[99],[45],[4],[9,11],[6],[81],[18,32],[20],[13],[42],[37,91],[36],[10,37],[96],[57],[20],[89],[18],[41,5],[23],[75],[7],[25,51],[48],[46],[29],[85],[82],[6],[38],[14],[1],[12],[42],[42],[83],[13],[14,20],[17,34],[36],[58],[2],[38],[33,59],[37],[15],[64],[56],[0],[40],[92],[63],[35],[62],[32]]