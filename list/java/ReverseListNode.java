import java.util.ArrayList;

/**
 * 输入一个链表，从尾到头打印链表每个节点的值。
 */
public class ReverseListNode {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> collect = new ArrayList<>();
        reverseListNode(listNode, collect);
        return collect;
    }

    private void reverseListNode(ListNode listNode, ArrayList<Integer> collect) {
        if (listNode != null) {
            reverseListNode(listNode.next, collect);
            collect.add(listNode.val);
        }
    }

}
